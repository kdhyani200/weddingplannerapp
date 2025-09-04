package kd.dhyani.weddingplannerapp.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.database.FirebaseDatabase
import kd.dhyani.weddingplannerapp.R
import kd.dhyani.weddingplannerapp.data.User

@Composable
fun SignUpScreen(
    onLoginClick: () -> Unit // Callback when "Log in" is clicked
) {
    // 🔹 State variables for user input
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) } // 🔹 Password visibility toggle

    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance() // Firebase Authentication instance
    val db = FirebaseDatabase.getInstance().reference.child("users") // Firebase Database reference

    // 🔹 Main container
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { focusManager.clearFocus() } // Dismiss keyboard on tap outside
    ) {
        // 🔹 Top background image
        Image(
            painter = painterResource(id = R.drawable.loginpage),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.Crop
        )

        // 🔹 Sign-up form container
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 200.dp)
                .clip(RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp))
                .background(Color.White)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 🔹 Header
            Text(
                text = "Sign Up",
                fontSize = 22.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                textAlign = TextAlign.Start,
                color = Color.Black
            )

            // 🔹 TextField color configuration
            val textFieldColors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = Color(0xFFddc57c),
                unfocusedIndicatorColor = Color.LightGray,
                cursorColor = Color(0xFFddc57c)
            )

            // 🔹 Input fields
            TextField(
                value = firstName,
                onValueChange = { firstName = it },
                placeholder = { Text("First Name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors
            )

            TextField(
                value = lastName,
                onValueChange = { lastName = it },
                placeholder = { Text("Last Name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors
            )

            TextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Email") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                colors = textFieldColors
            )

            TextField(
                value = gender,
                onValueChange = { gender = it },
                placeholder = { Text("Male/Female") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                colors = textFieldColors
            )

            // 🔹 Password field with visibility toggle
            TextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Password") },
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val icon = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = icon, contentDescription = "Toggle password visibility")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                colors = textFieldColors
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 🔹 Create Account Button
            Button(
                onClick = {
                    // Validation checks
                    if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || gender.isEmpty()) {
                        Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    if (password.length < 8 || password.length > 12) {
                        Toast.makeText(context, "Password must be 8-12 characters", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    val genderLower = gender.lowercase().trim()
                    if (genderLower != "male" && genderLower != "female") {
                        Toast.makeText(context, "Gender must be Male or Female", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    // 🔹 Firebase Authentication - Create User
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val uid = auth.currentUser?.uid ?: return@addOnCompleteListener
                                val user = User(firstName, lastName, email, genderLower)

                                // 🔹 Save user details to Firebase Realtime Database
                                db.child(uid).setValue(user)
                                    .addOnCompleteListener { saveTask ->
                                        if (saveTask.isSuccessful) {
                                            // 🔹 Send email verification
                                            auth.currentUser?.sendEmailVerification()
                                                ?.addOnSuccessListener {
                                                    Toast.makeText(context, "Verification email sent!", Toast.LENGTH_SHORT).show()
                                                }
                                                ?.addOnFailureListener {
                                                    Toast.makeText(context, "Failed: ${it.message}", Toast.LENGTH_SHORT).show()
                                                }
                                            Toast.makeText(context, "Account created successfully!", Toast.LENGTH_SHORT).show()
                                        } else {
                                            Toast.makeText(context, "Error saving data: ${saveTask.exception?.message}", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                            } else {
                                // 🔹 Handle errors like existing email
                                val exception = task.exception
                                if (exception is FirebaseAuthUserCollisionException) {
                                    Toast.makeText(context, "Email already registered. Try login", Toast.LENGTH_SHORT).show()
                                } else {
                                    Toast.makeText(context, "Error: ${exception?.message}", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFddc57c)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Create Account", color = Color(0xFF5e5840))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Or")

            Spacer(modifier = Modifier.height(16.dp))

            // 🔹 Login redirect
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Already have an account? ")
                Text(
                    text = "Log in",
                    color = Color(0xFF5e5840),
                    modifier = Modifier.clickable { onLoginClick() }
                )
            }
        }
    }
}

@Preview
@Composable
fun SignUpScreenPreview() {
    SignUpScreen({})
}

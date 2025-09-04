package kd.dhyani.weddingplannerapp.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kd.dhyani.weddingplannerapp.R
import kd.dhyani.weddingplannerapp.SessionManager
import kd.dhyani.weddingplannerapp.navigation.Screens
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

@Composable
fun ProfileScreen(navController: NavHostController, sessionManager: SessionManager) {
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()
    val db = FirebaseDatabase.getInstance().reference.child("users")

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var email by remember { mutableStateOf(auth.currentUser?.email ?: "") }

    // Fetch user data
    LaunchedEffect(auth.currentUser) {
        auth.currentUser?.uid?.let { uid ->
            try {
                val snapshot = db.child(uid).get().await()
                firstName = snapshot.child("firstName").getValue(String::class.java) ?: ""
                lastName = snapshot.child("lastName").getValue(String::class.java) ?: ""
                gender = snapshot.child("gender").getValue(String::class.java) ?: ""
            } catch (e: Exception) {
                Toast.makeText(context, "Failed to load profile", Toast.LENGTH_SHORT).show()
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(54.dp))

            Image(
                painter = painterResource(id = R.drawable.profile_placeholder),
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(160.dp)
                    .clip(CircleShape)
                    .border(4.dp, Color(0xFF706219), CircleShape)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ProfileItem(label = "First Name", value = firstName)
                ProfileItem(label = "Last Name", value = lastName)
                ProfileItem(label = "Gender", value = gender)
                ProfileItem(label = "Email", value = email)
            }

            Spacer(modifier = Modifier.height(40.dp))

            // 🔹 Logout button
            Button(
                onClick = {
                    // Logout from Firebase and DataStore
                    auth.signOut()
                    runBlocking { sessionManager.setLogin(false) }
                    navController.navigate(Screens.LOGIN) {
                        popUpTo(Screens.HOME) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                modifier = Modifier
                    .height(50.dp)
                    .width(180.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFddc57c)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Log Out", color = Color(0xFF5e5840))
            }
        }
    }
}


@Composable
fun ProfileItem(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color(0xFF333333)
        )
        Text(
            text = value,
            fontSize = 16.sp,
            color = Color(0xFF666666)
        )
    }
}

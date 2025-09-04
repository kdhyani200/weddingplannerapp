package kd.dhyani.weddingplannerapp.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kd.dhyani.weddingplannerapp.content.PhotographerSection
import kd.dhyani.weddingplannerapp.R
import kd.dhyani.weddingplannerapp.content.VenueSection
import kd.dhyani.weddingplannerapp.navigation.Screens
import kotlinx.coroutines.tasks.await

@Composable
fun HomeScreen(navController: NavController) {
    val dancingScript = FontFamily(Font(R.font.dancing_script))
    val secondline = FontFamily(Font(R.font.second_line))
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    val auth = FirebaseAuth.getInstance()
    val db = FirebaseDatabase.getInstance().reference.child("users")

    var firstName by remember { mutableStateOf("User") }

    // Fetch the first name (with safe fallbacks)
    LaunchedEffect(auth.currentUser) {
        val currentUser = auth.currentUser
        if (currentUser == null) {
            navController.navigate(Screens.LOGIN) {
                popUpTo(Screens.INTRO) { inclusive = true }
            }
            return@LaunchedEffect
        }

        val uid = currentUser.uid
        try {
            val snapshot = db.child(uid).get().await()

            val fromFirstName = snapshot.child("firstName").getValue(String::class.java)

            val fromName = snapshot.child("name").getValue(String::class.java)?.split(" ")?.firstOrNull()

            val fromDisplayName = currentUser.displayName?.split(" ")?.firstOrNull()

            firstName = when {
                !fromFirstName.isNullOrBlank() -> fromFirstName
                !fromName.isNullOrBlank() -> fromName
                !fromDisplayName.isNullOrBlank() -> fromDisplayName
                else -> "User"
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Failed to load user data", Toast.LENGTH_SHORT).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.final_home),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Text(
                text = "   Hello",
                color = Color(0xFF5e5840),
                fontSize = 65.sp,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 70.dp, start = 6.dp),
                fontFamily = dancingScript
            )
            Text(
                text = " $firstName!",
                color = Color(0xFF5e5840),
                fontSize = 65.sp,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 130.dp, start = 6.dp),
                fontFamily = dancingScript
            )
            Text(
                text = "            Plan your\n          special day\n              with us.",
                color = Color(0xFF5e5840),
                fontSize = 15.sp,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 205.dp, start = 6.dp),
                fontFamily = secondline,
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(top = 280.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFdcd0ac))
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PhotographerSection(navController)

                    Spacer(modifier = Modifier.height(16.dp))

                    VenueSection(navController)

                    Spacer(modifier = Modifier.height(16.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Text(
                            text = "More sections coming soon...",
                            modifier = Modifier.padding(16.dp),
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }

        NavBar(navController = navController)
    }
}

@Composable
fun NavBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 35.dp, start = 8.dp, end = 8.dp)
            .background(color = Color(0xFFdcd0ac)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
    ) {
        IconButton(onClick = { /* TODO */ }) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF706219)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            IconButton(onClick = { /* TODO */ }) {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF706219)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingBag,
                        contentDescription = "Shopping",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            IconButton(onClick = { navController.navigate("profile") }) {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF706219)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Profile",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController)
}

package kd.dhyani.weddingplannerapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kd.dhyani.weddingplannerapp.data.PhotographerData
import kd.dhyani.weddingplannerapp.data.photographers

@Composable
fun MaxPhotographerPage(
    photographer: PhotographerData,        // Photographer data to display
    navController: NavController? = null   // Optional navController for navigation
) {
    // 🔹 Main container
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // 🔹 Scrollable column for photographer details
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 80.dp) // Leave space for bottom "Book Now" button
        ) {
            // 🔹 Top image of photographer
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                Image(
                    painter = painterResource(id = photographer.imageRes),
                    contentDescription = "Photographer Profile",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            // 🔹 Photographer information section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Name
                Text(
                    text = photographer.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Rating and number of projects
                Text(
                    text = "⭐ ${photographer.rating} | ${photographer.projects} projects",
                    fontSize = 16.sp,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Specialties and price range
                Text(
                    text = "Specialties: ${photographer.specialties}",
                    fontSize = 16.sp,
                    color = Color.DarkGray
                )

                Text(
                    text = "Price Range: ${photographer.priceRange}",
                    fontSize = 16.sp,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(12.dp))

                // About section title
                Text(
                    text = "About Photographer:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                // Photographer description
                Text(
                    text = photographer.description,
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }

        // 🔹 Bottom "Book Now" button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(Color.White)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { /* TODO: Handle booking */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF706219)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Book Now", fontSize = 18.sp, color = Color.Black)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MaxPhotographerPagePreview() {
    MaxPhotographerPage(photographers.first(), navController = null)
}

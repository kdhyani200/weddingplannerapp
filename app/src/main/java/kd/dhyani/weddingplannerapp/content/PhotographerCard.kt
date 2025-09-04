package kd.dhyani.weddingplannerapp.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kd.dhyani.weddingplannerapp.data.PhotographerData
import kd.dhyani.weddingplannerapp.data.photographers

// Single card for a photographer
@Composable
fun PhotographerCard(
    photographer: PhotographerData,
    index: Int,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .width(180.dp)
            .padding(12.dp)
            .height(200.dp)
            .clickable {
                // Navigate to detail page for this photographer, passing the index
                navController.navigate("photographerDetail/$index")
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp) // Slight shadow for depth
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White), // White background for the card
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile image of photographer
            Image(
                painter = painterResource(id = photographer.imageRes),
                contentDescription = "Photographer Profile",
                modifier = Modifier.size(160.dp),
                contentScale = ContentScale.Crop // Crop to fit nicely
            )

            // Info box at the bottom of the card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(color = Color(0xFFD6CFB4)) // Light brown background
            ) {
                // Photographer name
                Text(
                    modifier = Modifier.padding(start = 8.dp, top = 5.dp),
                    text = photographer.name,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                // Rating display
                Text(
                    modifier = Modifier.padding(start = 120.dp, top = 6.dp),
                    text = "${photographer.rating} ★",
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )

                // Experience/projects info
                Text(
                    modifier = Modifier.padding(start = 8.dp, top = 18.dp),
                    text = "${photographer.projects} projects exp",
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

// Horizontal scrolling list of photographer cards
@Composable
fun PhotographerList(navController: NavController) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 0.dp)
    ) {
        // Create a card for each photographer
        itemsIndexed(photographers) { index, photographer ->
            PhotographerCard(photographer, index, navController)
        }
    }
}

// Section wrapper containing heading and photographer list
@Composable
fun PhotographerSection(navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(265.dp)
            .padding(0.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp) // Slightly higher elevation than individual cards
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFDEDEDE)) // Grayish background for section
                .padding(10.dp)
        ) {
            // Section title
            Text(
                text = "Hire a Photographer",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(start = 12.dp)
            )

            // The horizontal list of photographers
            PhotographerList(navController)
        }
    }
}

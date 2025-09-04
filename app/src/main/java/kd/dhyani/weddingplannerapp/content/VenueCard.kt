package kd.dhyani.weddingplannerapp.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kd.dhyani.weddingplannerapp.data.MaxVenueData
import kd.dhyani.weddingplannerapp.data.venues

// Single card for a venue
@Composable
fun VenueCard(
    venue: MaxVenueData,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .width(180.dp)
            .padding(12.dp)
            .height(220.dp)
            .clickable {
                // Navigate to venue detail page, passing the venue name
                navController.navigate("maxVenue/${venue.name}")
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp) // Slight shadow for depth
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White), // White card background
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Venue image
            Image(
                painter = painterResource(id = venue.imageRes),
                contentDescription = "Venue Image",
                modifier = Modifier.size(160.dp),
                contentScale = ContentScale.Crop // Crop image to fit nicely
            )

            // Info box with venue details
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(color = Color(0xFFD6CFB4)) // Light brown background
            ) {
                Text(
                    modifier = Modifier.padding(start = 8.dp, top = 5.dp),
                    text = venue.name,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    modifier = Modifier.padding(start = 8.dp, top = 18.dp),
                    text = "Capacity: ${venue.capacity}", // Shows venue capacity
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    modifier = Modifier.padding(start = 8.dp, top = 30.dp),
                    text = "Price: ${venue.priceRange}", // Shows venue price range
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

// Horizontal scrolling list of venue cards
@Composable
fun VenueList(navController: NavController) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 0.dp)
    ) {
        items(venues) { venue ->
            VenueCard(venue, navController)
        }
    }
}

// Section wrapper containing heading and venue list
@Composable
fun VenueSection(navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(275.dp)
            .padding(0.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp) // Slightly higher than individual cards
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFDEDEDE)) // Grayish background for section
                .padding(10.dp)
        ) {
            // Section title
            Text(
                text = "Book a Venue",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(start = 12.dp)
            )

            // Horizontal list of venues
            VenueList(navController)
        }
    }
}

// Preview for Compose UI in Android Studio
@Preview(showBackground = true)
@Composable
fun VenueListPreview() {
    val navController = rememberNavController()
    VenueSection(navController)
}

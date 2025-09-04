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
import kd.dhyani.weddingplannerapp.data.MaxVenueData
import kd.dhyani.weddingplannerapp.data.venues

@Composable
fun MaxVenuePage(
    venue: MaxVenueData,                // Venue data to display
    navController: NavController? = null // Optional NavController for navigation
) {
    // 🔹 Main container for the page
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // 🔹 Scrollable column for venue details
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 80.dp) // Leave space for the "Book Now" button
        ) {
            // 🔹 Top image of the venue
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                Image(
                    painter = painterResource(id = venue.imageRes),
                    contentDescription = "Venue Image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            // 🔹 Venue information section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Venue name
                Text(
                    text = venue.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Venue details: capacity, price, location
                Text(
                    text = "Capacity: ${venue.capacity} guests",
                    fontSize = 16.sp,
                    color = Color.DarkGray
                )

                Text(
                    text = "Price: ${venue.priceRange}",
                    fontSize = 16.sp,
                    color = Color.DarkGray
                )

                Text(
                    text = "Location: ${venue.location}",
                    fontSize = 16.sp,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Facilities / Important info
                Text(
                    text = "Facilities / Important Info:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Text(
                    text = venue.facilities,
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Venue description
                Text(
                    text = "Description:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Text(
                    text = venue.description,
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
fun MaxVenuePagePreview() {
    // Preview the venue page with the first venue in the list
    MaxVenuePage(venues.first(), navController = null)
}

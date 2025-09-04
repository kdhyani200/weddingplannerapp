package kd.dhyani.weddingplannerapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class Guest(val name: String, var rsvp: String)

@Composable
fun GuestListScreen() {
    var guestName by remember { mutableStateOf("") }
    var guests by remember { mutableStateOf(listOf<Guest>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Guest List Management", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = guestName,
            onValueChange = { guestName = it },
            label = { Text("Enter Guest Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (guestName.isNotBlank()) {
                    guests = guests + Guest(guestName, "Pending")
                    guestName = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Guest")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(guests) { guest ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(guest.name, style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                onClick = { guest.rsvp = "Yes" },
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                            ) { Text("Yes") }
                            Button(
                                onClick = { guest.rsvp = "No" },
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                            ) { Text("No") }
                            Button(
                                onClick = { guest.rsvp = "Maybe" },
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                            ) { Text("Maybe") }
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text("RSVP: ${guest.rsvp}")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun GuestListScreenPreview() {
    GuestListScreen()
}
package kd.dhyani.weddingplannerapp.data

import kd.dhyani.weddingplannerapp.R

// Data class representing a photographer with all details
data class PhotographerData(
    val name: String,          // Photographer's full name
    val rating: Double,        // Average rating out of 5
    val projects: Int,         // Number of completed projects/events
    val imageRes: Int,         // Drawable resource ID for profile image
    val specialties: String,   // Photographer's specialties (e.g., wedding, candid)
    val priceRange: String,    // Price range for hiring
    val description: String    // Short bio or description of the photographer
)

// 🔹 Sample list of photographers to display in the app
val photographers = listOf(
    PhotographerData(
        "John Doe",
        4.5,
        67,
        R.drawable.photo_1,          // Profile image for John Doe
        "Weddings, Candid, Traditional",
        "$1500 - $3000",
        "John Doe is an experienced wedding photographer known for candid and traditional shots."
    ),
    PhotographerData(
        "Emma Stone",
        4.8,
        120,
        R.drawable.photo_1,          // Profile image for Emma Stone
        "Destination Weddings, Cinematic",
        "$2000 - $5000",
        "Emma specializes in destination weddings and cinematic photography to make your memories timeless."
    ),
    PhotographerData(
        "Raj Kumar",
        4.6,
        95,
        R.drawable.photo_1,          // Profile image for Raj Kumar
        "Pre-Wedding, Traditional",
        "$1200 - $2500",
        "Raj Kumar brings creativity and cultural essence into pre-wedding and traditional photography."
    ),
    PhotographerData(
        "Sophia Lee",
        4.7,
        88,
        R.drawable.photo_1,          // Profile image for Sophia Lee
        "Candid, Modern Weddings",
        "$1800 - $4000",
        "Sophia captures your moments in a modern style with a natural candid touch."
    )
)

package kd.dhyani.weddingplannerapp.data

import kd.dhyani.weddingplannerapp.R

// Data class representing a venue with all details
data class MaxVenueData(
    val name: String,         // Venue name
    val capacity: Int,        // Maximum number of guests it can accommodate
    val priceRange: String,   // Price range for booking
    val location: String,     // City/location of the venue
    val facilities: String,   // List of facilities/services available
    val description: String,  // Detailed description of the venue
    val imageRes: Int         // Drawable resource ID for venue image
)

// 🔹 Sample list of venues to display in the app
val venues = listOf(
    MaxVenueData(
        name = "Royal Palace",
        capacity = 300,
        priceRange = "$5000 - $10000",
        location = "New Delhi",
        facilities = "Parking, AC, Stage, Catering Available",
        description = "Royal Palace offers a grand and elegant ambiance, perfect for luxurious weddings and events. " +
                "The venue features exquisite decor, lush gardens, and spacious halls that can accommodate large gatherings. " +
                "Professional catering services, ample parking, and premium facilities ensure a seamless and memorable experience. " +
                "Whether you're planning a traditional ceremony or a modern celebration, Royal Palace provides the perfect backdrop for your special day.",
        imageRes = R.drawable.venue_1 // Image for Royal Palace
    ),
    MaxVenueData(
        name = "Garden View",
        capacity = 150,
        priceRange = "$2000 - $5000",
        location = "Bangalore",
        facilities = "Open Lawn, Garden Seating, Catering Options, Parking",
        description = "Garden View is a charming outdoor venue surrounded by lush greenery, ideal for intimate weddings and gatherings. " +
                "It offers flexible seating arrangements, beautiful landscaping, and dedicated staff to ensure a seamless event. " +
                "Perfect for couples who want a serene and picturesque celebration with natural beauty as the backdrop.",
        imageRes = R.drawable.venue_1 // Image for Garden View
    ),
    MaxVenueData(
        name = "Ocean Breeze",
        capacity = 200,
        priceRange = "$4000 - $8000",
        location = "Goa",
        facilities = "Beachfront, Sea View, Open-Air Dining, Live Music Options",
        description = "Ocean Breeze provides a stunning beachfront setting for weddings and events, offering panoramic sea views. " +
                "The venue includes spacious decks, comfortable seating, and customizable decoration options. " +
                "Ideal for couples looking for a vibrant and scenic seaside celebration with an unforgettable atmosphere.",
        imageRes = R.drawable.venue_1 // Image for Ocean Breeze
    ),
    MaxVenueData(
        name = "Grand Ballroom",
        capacity = 400,
        priceRange = "$8000 - $15000",
        location = "Mumbai",
        facilities = "Indoor Hall, Stage, AV System, Parking, Catering Available",
        description = "Grand Ballroom is a luxurious indoor venue perfect for large weddings, receptions, and corporate events. " +
                "It features high ceilings, elegant chandeliers, and modern audio-visual facilities. " +
                "With ample parking and professional event support, it ensures a grand and memorable celebration for all guests.",
        imageRes = R.drawable.venue_1 // Image for Grand Ballroom
    )
)

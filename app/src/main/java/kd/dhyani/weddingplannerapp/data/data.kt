package kd.dhyani.weddingplannerapp.data

// Data class to hold user information
data class User(
    val firstName: String = "", // User's first name, default empty
    val lastName: String = "",  // User's last name, default empty
    val email: String = "",     // User's email, default empty
    val gender: String = ""     // User's gender, default empty
)

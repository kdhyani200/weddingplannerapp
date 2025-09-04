package kd.dhyani.weddingplannerapp.content

import androidx.annotation.DrawableRes  // Marks this int as a drawable resource
import kd.dhyani.weddingplannerapp.R  // Access to app's images and other resources

// Simple data class to hold info for each page in a horizontal pager/slider
data class HorizontalPagerContent(
    val title: String,                // The heading/title for the page
    @DrawableRes val res: Int,        // Image for the page (must be a drawable)
    val des: String                   // Description or subtitle for the page
)

// Function to return a list of pages for the intro slider
fun getList(): List<HorizontalPagerContent> {
    return listOf(
        HorizontalPagerContent(
            "Plan Your Dream Wedding",
            R.drawable.firstintro,   // Image for first page
            "From the first ‘Yes’ to the final ‘I do’, we’ll help you organize every detail of your special day."
        ),
        HorizontalPagerContent(
            "Organize with Ease",
            R.drawable.secondintro,  // Image for second page
            "Manage guest lists, venues, vendors, and budgets effortlessly — all in one place."
        ),
        HorizontalPagerContent(
            "Capture Your Memories",
            R.drawable.secondintro,  // Reusing the second image, keeps design consistent
            "Keep track of ideas, inspirations, and magical moments while we handle the planning."
        ),
        HorizontalPagerContent(
            "Welcome",
            R.drawable.firstintro,   // Using first image again, maybe for a welcome screen
            ""                        // Empty description, maybe just a visual welcome
        )
    )
}

package kd.dhyani.weddingplannerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import kd.dhyani.weddingplannerapp.navigation.AppNavGraph
import kd.dhyani.weddingplannerapp.screens.SignUpScreen
import kd.dhyani.weddingplannerapp.ui.theme.WeddingPlannerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        // 🔹 Enable edge-to-edge display (status bar and navigation bar transparent)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                android.graphics.Color.TRANSPARENT, android.graphics.Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.light(
                android.graphics.Color.TRANSPARENT, android.graphics.Color.TRANSPARENT
            )
        )

        super.onCreate(savedInstanceState)

        val sessionManager = SessionManager(this)

        // 🔹 Set Jetpack Compose content for the activity
        setContent {
            // 🔹 Apply app theme
            WeddingPlannerAppTheme {

                // 🔹 Surface provides background color from theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // 🔹 Create NavController for navigation between screens
                    val navController = rememberNavController()

                    // 🔹 Call the main navigation graph
                    AppNavGraph(
                        navController = navController,
                        sessionManager = sessionManager
                    )
                }
            }
        }
    }
}

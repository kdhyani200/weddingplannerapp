package kd.dhyani.weddingplannerapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.firebase.auth.FirebaseAuth
import kd.dhyani.weddingplannerapp.SessionManager
import kd.dhyani.weddingplannerapp.content.VenueSection
import kd.dhyani.weddingplannerapp.data.photographers
import kd.dhyani.weddingplannerapp.data.venues
import kd.dhyani.weddingplannerapp.screens.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@Composable
fun AppNavGraph(navController: NavHostController, sessionManager: SessionManager) {

    val auth = FirebaseAuth.getInstance()

    // 🔹 Observe login state from DataStore safely
    val isLoggedIn by sessionManager.isLoggedIn.collectAsState(initial = false)

    // 🔹 Decide start screen
    val startDestination = if (auth.currentUser != null && isLoggedIn) {
        Screens.HOME
    } else {
        Screens.INTRO
    }

    NavHost(navController = navController, startDestination = startDestination) {

        // Intro Screen
        composable(Screens.INTRO) {
            IntroScreen(
                onLoginClick = { navController.navigate(Screens.LOGIN) },
                onSignUpClick = { navController.navigate(Screens.SIGNUP) }
            )
        }

        // Login Screen
        composable(Screens.LOGIN) {
            LoginScreen(
                sessionManager = sessionManager,   // <-- Pass it here
                onSignUpClick = { navController.navigate(Screens.SIGNUP) },
                onLoginSuccess = {
                    runBlocking { sessionManager.setLogin(true) }
                    navController.navigate(Screens.HOME) {
                        popUpTo(Screens.INTRO) { inclusive = true }
                        popUpTo(Screens.LOGIN) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }


        // SignUp Screen
        composable(Screens.SIGNUP) {
            SignUpScreen(
                onLoginClick = { navController.navigate(Screens.LOGIN) }
            )
        }

        // Home Screen
        composable(Screens.HOME) {
            HomeScreen(navController)
        }

        // Venue Section screen
        composable(Screens.VENUES) {
            VenueSection(navController)
        }

        // MaxVenuePage with dynamic venueName
        composable(
            route = "${Screens.MAX_VENUE}/{venueName}",
            arguments = listOf(navArgument("venueName") { type = NavType.StringType })
        ) { backStackEntry ->
            val venueName = backStackEntry.arguments?.getString("venueName")
            val selectedVenue = venues.find { it.name == venueName }
            selectedVenue?.let { MaxVenuePage(it) }
        }

        // Photographer detail page
        composable("photographerDetail/{index}") { backStackEntry ->
            val index = backStackEntry.arguments?.getString("index")?.toInt() ?: 0
            MaxPhotographerPage(photographers[index], navController)
        }

        // Profile screen
        composable(Screens.PROFILE) {
            ProfileScreen(navController = navController, sessionManager = sessionManager)
        }
    }
}


// Constants for screen routes
object Screens {
    const val INTRO = "intro"
    const val LOGIN = "login"
    const val SIGNUP = "signup"
    const val HOME = "home"
    const val VENUES = "venues"
    const val MAX_VENUE = "maxVenue"
    const val PROFILE = "profile"
}

package com.nicholas.rutherford.moments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.nicholas.rutherford.moments.ui.theme.MomentsTheme
import org.koin.android.ext.android.get

/**
 * The `MainActivity` class is the entry point of the application. It serves as the container for
 * the main UI elements of the app, setting up the necessary dependencies, theme, and navigation.
 */
class MainActivity : ComponentActivity() {

    /**
     * Called when the activity is first created. This method sets up the layout for the activity,
     * including enabling edge-to-edge display, applying the app's theme, and setting up the navigation
     * system using Jetpack Compose.
     *
     * The `setContent` block initializes the composables and provides the necessary navigation controller
     * and navigator injected by Koin.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enables edge-to-edge display, removing system UI insets (e.g., status bar, navigation bar).
        enableEdgeToEdge()

        // Sets the composable content of the activity. The content is wrapped inside the MomentsTheme.
        setContent {
            MomentsTheme {
                // Sets up the navigation system with the navHostController and navigator.
                // The navHostController is responsible for managing navigation within the app.
                NavigationComponent(
                    application = this.application, // The application instance for accessing resources
                    navHostController = rememberNavController(), // A NavController is created to manage navigation
                    navigator = get() // Retrieves the Navigator instance using Koin dependency injection
                )
            }
        }
    }
}

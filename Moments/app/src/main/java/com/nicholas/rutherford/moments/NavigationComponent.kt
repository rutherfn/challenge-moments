package com.nicholas.rutherford.moments

import android.app.Application
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.nicholas.rutherford.moments.composeext.AlertDialog
import com.nicholas.rutherford.moments.composeext.FloatingActionButtonAnimated
import com.nicholas.rutherford.moments.navigation.Alert
import com.nicholas.rutherford.moments.navigation.AlertConfirmAndDismissButton
import com.nicholas.rutherford.moments.navigation.NavHostDefinitions.createEditMomentScreen
import com.nicholas.rutherford.moments.navigation.NavHostDefinitions.homeScreen
import com.nicholas.rutherford.moments.navigation.NavigationActions
import com.nicholas.rutherford.moments.navigation.NavigationDestinations
import com.nicholas.rutherford.moments.navigation.Navigator
import com.nicholas.rutherford.moments.navigation.asLifecycleAwareState
import com.nicholas.rutherford.moments.ui.theme.Blue40
import com.nicholas.rutherford.moments.ui.theme.OffWhite

/**
 * A Composable function that handles the navigation logic and UI for the app.
 * It serves as the main navigation host for the application, managing transitions between screens
 * and providing a floating action button (FAB) that triggers navigation to the Create/Edit Moment screen.
 *
 * The function uses Koin for dependency injection and handles lifecycle-aware navigation using
 * `Navigator` and `NavHostController`.
 *
 * @param application An instance of [Application] for accessing application-specific resources.
 * @param navHostController The NavHostController to manage navigation.
 * @param navigator An instance of [Navigator] for managing navigation actions.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationComponent(
    application: Application,
    navHostController: NavHostController,
    navigator: Navigator
) {
    // State variables
    var alert: Alert? by remember { mutableStateOf(value = null) }
    var isPressed by remember { mutableStateOf(false) }
    var showSnackbar by remember { mutableStateOf(false) }
    var showFab by remember { mutableStateOf(true) }
    var showNavigationIcon by remember { mutableStateOf(false) }

    // Get lifecycle owner for lifecycle-aware state management
    val lifecycleOwner = LocalLifecycleOwner.current

    // Observe navigation actions and pop route actions from the Navigator
    val alertState by navigator.alertActions.asLifecycleAwareState(
        lifecycleOwner = lifecycleOwner,
        initialState = null
    )
    val navigatorState by navigator.navActions.asLifecycleAwareState(
        lifecycleOwner = lifecycleOwner,
        initialState = null
    )
    val popRouteState by navigator.popRouteActions.asLifecycleAwareState(
        lifecycleOwner = lifecycleOwner,
        initialState = null
    )

    // Effect to trigger navigation when there's a new navigation action
    LaunchedEffect(navigatorState) {
        navigatorState?.let {
            navHostController.navigate(it.destination, it.navOptions)
        }
    }

    // Effect to trigger alert when there's a new alert action
    LaunchedEffect(alertState) {
        alertState?.let { newAlert ->
            alert = newAlert
        }
    }

    // Effect to handle pop route actions (navigate back)
    LaunchedEffect(popRouteState) {
        popRouteState?.let { route ->
            if (route == NavigationDestinations.HOME_SCREEN) {
                showNavigationIcon = false
                showFab = true
            }
            navHostController.popBackStack(route, false)
            navigator.pop(popRouteAction = null) // Reset pop route action
        }
    }

    // Scaffold to wrap the top bar and floating action button (FAB)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            if (showFab) {
                // FAB setup: triggers navigation to the Create/Edit Moment screen
                FloatingActionButtonAnimated(
                    isPressed = isPressed,
                    onClick = {
                        isPressed = true
                        navigator.navigate(navigationAction = NavigationActions.createEditMoment("", -1))
                    },
                    onAnimationEnd = {
                        isPressed = false
                        showSnackbar = true
                    }
                )
            }
        },
        topBar = {
            // Top app bar with a title and styling
            TopAppBar(
                title = {
                    Text(
                        text = application.getStringResource(stringId = Constants.StringIds.APP_NAME),
                        color = OffWhite
                    )
                },
                navigationIcon = {
                    if (showNavigationIcon) {
                    IconButton(onClick = { navigator.pop(NavigationDestinations.HOME_SCREEN) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = OffWhite
                        )
                    }
                        }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Blue40,
                    titleContentColor = OffWhite,
                    navigationIconContentColor = OffWhite,
                    actionIconContentColor = OffWhite
                )
            )
        }
    ){ paddingValues ->
        NavHost(
            navController = navHostController,
            modifier = Modifier
                .padding(paddingValues),
            startDestination = NavigationDestinations.HOME_SCREEN
        ) {

            this.homeScreen(
                { shouldShowFab -> showFab = shouldShowFab },
                { shouldShowNavigationIcon -> showNavigationIcon = shouldShowNavigationIcon }
            ) // home screen content
            this.createEditMomentScreen(
                { shouldShowFab -> showFab = shouldShowFab },
                { shouldShowNavigationIcon -> showNavigationIcon = shouldShowNavigationIcon }
            ) // create edit moment content
        }

        alert?.let { newAlert ->
            AlertDialog(
                onDismissClicked = {
                    navigator.alert(alertAction = null)
                    alert = null
                    newAlert.onDismissClicked?.invoke()
                },
                title = newAlert.title,
                confirmButton = newAlert.confirmButton?.let { confirmButton ->
                    AlertConfirmAndDismissButton(
                        onButtonClicked = {
                            navigator.alert(alertAction = null)
                            alert = null
                            confirmButton.onButtonClicked?.invoke()
                        },
                        buttonText = confirmButton.buttonText
                    )
                } ?: run { null },
                dismissButton = newAlert.dismissButton?.let { dismissButton ->
                    AlertConfirmAndDismissButton(
                        onButtonClicked = {
                            navigator.alert(alertAction = null)
                            alert = null
                            dismissButton.onButtonClicked?.invoke()
                        },
                        buttonText = dismissButton.buttonText
                    )
                } ?: run { null },
                description = newAlert.description
            )
        }
    }
}

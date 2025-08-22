package com.nicholas.rutherford.moments

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.nicholas.rutherford.moments.createeditmoment.CreateEditMomentScreen
import com.nicholas.rutherford.moments.createeditmoment.CreateEditMomentViewModel
import com.nicholas.rutherford.moments.home.HomeScreen
import com.nicholas.rutherford.moments.home.HomeViewModel
import com.nicholas.rutherford.moments.navigation.NavigationDestinations
import org.koin.androidx.compose.koinViewModel

object NavHostDefinitions {

    /**
     * Extension function to add the home screen to the navigation graph.
     *
     * @param showFab A lambda function used to show or hide the Floating Action Button (FAB).
     *                It is called with a `Boolean` indicating whether the FAB should be shown.
     * @param showNavigationIcon A lambda function used to show or hide the Navigation Icon.
     *                It is called with a `Boolean` indicating whether the Navigation Icon should be shown.
     */
    fun NavGraphBuilder.homeScreen(showFab: (Boolean) -> Unit, showNavigationIcon: (Boolean) -> Unit) {
        composable(route = NavigationDestinations.HOME_SCREEN) {
            // Show the FAB for this screen
            showFab(true)
            showNavigationIcon(false)

            // Obtain the ViewModel for the Home Screen using Koin dependency injection
            val homeViewModel: HomeViewModel = koinViewModel()

            // Observe lifecycle changes for the ViewModel
            ObserveLifecycle(homeViewModel)

            HomeScreen(
                notes = homeViewModel.state.collectAsState().value.notes,
                onItemClicked = { title, categoryTag ->
                    homeViewModel.onItemClicked(title = title, categoryTag = categoryTag)
                }
            )
        }
    }

    /**
     * Extension function to add the CreateEditMoment screen to the navigation graph.
     *
     * @param showFab A lambda function used to show or hide the Floating Action Button (FAB).
     *                It is called with a `Boolean` indicating whether the FAB should be hidden on this screen.
     * @param showNavigationIcon A lambda function used to show or hide the Navigation Icon.
     *                It is called with a `Boolean` indicating whether the Navigation Icon should be shown.
     */
    fun NavGraphBuilder.createEditMomentScreen(showFab: (Boolean) -> Unit, showNavigationIcon: (Boolean) -> Unit) {
        composable(
            route = NavigationDestinations.CREATE_EDIT_MOMENT_SCREEN_WITH_PARAMS,
            arguments = Constants.NavigationArguments.CREATE_EDIT_MOMENT_ARGS
        ) { entry ->
            // Hide the FAB for this screen
            showFab(false)
            showNavigationIcon(true)

            // Obtain the ViewModel for the CreateEditMomentScreen using Koin dependency injection
            val createEditMomentViewModel: CreateEditMomentViewModel = koinViewModel()

            // Observe lifecycle changes for the ViewModel
            ObserveLifecycle(createEditMomentViewModel)

            // Extract the arguments passed to this composable
            entry.arguments?.let { arguments ->
                CreateEditMomentScreen(
                    state = createEditMomentViewModel.state.collectAsState().value,
                    isEditing = !arguments.getString(Constants.NavigationKeys.PARAM_TITLE).isNullOrEmpty(),
                    onToolbarBackButton = { createEditMomentViewModel.onToolbarBackButton() },
                    onTitleValueChanged = { title -> createEditMomentViewModel.onTitleValueChanged(title) },
                    onCategorySelected = { categoryTag -> createEditMomentViewModel.onCategorySelected(categoryTag) },
                    onCreateButtonClicked = { createEditMomentViewModel.onCreateButtonClicked() },
                    onEditButtonClicked = { createEditMomentViewModel.onEditButtonClicked() },
                    onDeleteButtonClicked = { createEditMomentViewModel.onDeleteButtonClicked() }
                    )
            }
        }
    }
}





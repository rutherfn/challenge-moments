package com.nicholas.rutherford.moments.navigation

import androidx.navigation.NavOptions
import com.nicholas.rutherford.moments.Constants
import com.nicholas.rutherford.moments.R

/**
 * Object containing predefined navigation actions for the app.
 *
 * The `NavigationActions` object centralizes navigation logic and makes it easier to define
 * common navigation actions, such as navigating to the "Create/Edit Moment" screen, by using
 * a specific function to construct the necessary destination and options.
 */
object NavigationActions {

    /**
     * Creates a navigation action to navigate to the "Create/Edit Moment" screen.
     *
     * This function builds a [NavigationAction] object to navigate to a screen where the user
     * can either create a new moment or edit an existing one, depending on the parameters provided.
     *
     * If the title or category ID is provided, they are passed as query parameters in the destination URL.
     *
     * @param title The title of the moment to be created or edited. It can be `null` if it's a new moment.
     * @param categoryId The category ID associated with the moment. If it's a valid integer (greater than or equal to 0),
     *                   it will be added as a query parameter to the destination route.
     * @return A [NavigationAction] object that can be used to navigate to the "Create/Edit Moment" screen.
     */
    fun createEditMoment(title: String?, categoryId: Int) = object : NavigationAction {
        override val destination: String = buildString {
            // Start building the route using the base route defined in Constants
            append(Constants.NavigationKeys.CREATE_EDIT_MOMENT_ROUTE)

            // Collect any query parameters to pass along in the URL
            val queryParams = mutableListOf<String>()

            // Add the title as a query parameter if it's not null
            title?.let { queryParams += "${Constants.NavigationKeys.PARAM_TITLE}=$it" }

            // Add the category ID as a query parameter if it's valid (>= 0)
            if (categoryId >= 0) {
                queryParams += "${Constants.NavigationKeys.PARAM_CATEGORY_ID}=$categoryId"
            }

            // If there are any query parameters, append them to the route
            if (queryParams.isNotEmpty()) {
                append("?")
                append(queryParams.joinToString("&"))
            }
        }

        override val navOptions = NavOptions.Builder()
            .setEnterAnim(R.anim.fade_in)       // Fade in animation
            .setExitAnim(R.anim.fade_out)       // Fade out animation
            .setPopEnterAnim(R.anim.fade_in)    // Fade in on pop
            .setPopExitAnim(R.anim.fade_out)    // Fade out on pop
            .build()
    }
}

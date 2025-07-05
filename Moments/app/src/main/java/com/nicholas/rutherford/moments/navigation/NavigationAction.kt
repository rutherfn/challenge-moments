package com.nicholas.rutherford.moments.navigation

import androidx.navigation.NavOptions

/**
 * Interface representing an action for navigation within the app.
 *
 * This interface defines the necessary properties for a navigation action, such as
 * the destination route and optional navigation options. Any class implementing this
 * interface can be used to define custom navigation actions within the app.
 *
 * @property destination The route (destination) to navigate to.
 * @property navOptions The [NavOptions] associated with this navigation action,
 *          which can be used to specify custom navigation behaviors (e.g., animations, pop behavior).
 *          Defaults to an empty [NavOptions] if not specified.
 */
interface NavigationAction {
    /**
     * The destination route to navigate to. This is typically the string identifier for the screen.
     */
    val destination: String

    /**
     * The navigation options for this action, such as pop behavior or custom transition animations.
     * By default, it returns an empty [NavOptions] object.
     */
    val navOptions: NavOptions
        get() = NavOptions.Builder().build()
}

package com.nicholas.rutherford.moments.navigation

/**
 * Object containing the destination routes for navigation within the app.
 *
 * The `NavigationDestinations` object holds constants representing the various screen routes in the app.
 * These routes are used in conjunction with the navigation actions to define where users should navigate.
 */
object NavigationDestinations {

    /**
     * Route for the Home Screen.
     *
     * This destination represents the Home Screen, where users can see a list of moments or an empty state
     * if no moments are available.
     */
    const val HOME_SCREEN = "homeScreen"

    /**
     * Route for the Create/Edit Moment Screen with dynamic parameters.
     *
     * This destination represents the screen for creating a new moment or editing an existing one.
     * The route includes two query parameters:
     * 1. `title` - The title of the moment (can be `null` for a new moment).
     * 2. `categoryId` - The ID of the category associated with the moment.
     *
     * Example of a route with parameters:
     * ```
     * create_edit_moment_screen?title=MyMoment&categoryId=2
     * ```
     * If the moment is being created, `title` could be `null` or empty.
     */
    const val CREATE_EDIT_MOMENT_SCREEN_WITH_PARAMS = "create_edit_moment_screen?title={title}&categoryId={categoryId}"
}

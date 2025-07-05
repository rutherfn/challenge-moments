package com.nicholas.rutherford.moments.navigation

import kotlinx.coroutines.flow.StateFlow

/**
 * The [Navigator] interface defines the contract for a navigation manager in your application.
 * It handles navigation actions, route popping, and progress state visibility.
 *
 * This interface is implemented to manage screen navigation, show or hide progress indicators,
 * and pop routes from the navigation stack.
 */
interface Navigator {

    /**
     * A [StateFlow] representing the [Alert] action to be executed.
     * This flow is used to trigger different alerts, that get passed in.
     */
    val alertActions: StateFlow<Alert?>

    /**
     * A [StateFlow] representing the current navigation action to be executed.
     * This flow is used to trigger navigation events, such as navigating to a new screen.
     *
     * A `null` value represents no active navigation action.
     */
    val navActions: StateFlow<NavigationAction?>

    /**
     * A [StateFlow] representing the current route to be popped from the navigation stack.
     * When set, it triggers the app to navigate back to the previous screen or route.
     *
     * A `null` value means there is no active pop route action.
     */
    val popRouteActions: StateFlow<String?>

    /**
     * A [StateFlow] that determines whether the progress indicator should be shown.
     *
     * A value of `true` means a progress indicator should be displayed, while `false`
     * means no progress indicator is required.
     */
    val shouldShowProgressAction: StateFlow<Boolean>

    /**
     * Enables showing a alert with what is passed in, typically used when wanting to convey info to user
     * (e.g., something went wrong, something was updated, etc).
     */
    fun alert(alertAction: Alert?)

    /**
     * Enables the progress indicator, typically used when starting a long-running operation
     * (e.g., network request or data loading).
     */
    fun enableProgress()

    /**
     * Disables the progress indicator, usually after the completion of an operation
     * or when navigation has finished.
     */
    fun disableProgress()

    /**
     * Triggers the navigation action. It takes a [NavigationAction] that defines the destination
     * and any additional parameters required for the navigation.
     *
     * @param navigationAction The navigation action to be executed.
     */
    fun navigate(navigationAction: NavigationAction?)

    /**
     * Pops the current route from the navigation stack, returning to the previous screen.
     * It takes an optional route string that specifies which route to pop.
     *
     * @param popRouteAction The route to pop from the stack, or `null` if no specific route needs to be popped.
     */
    fun pop(popRouteAction: String?)
}

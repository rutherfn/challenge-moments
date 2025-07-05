package com.nicholas.rutherford.moments.navigation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * [NavigatorImpl] is a concrete implementation of the [Navigator] interface.
 * It handles the state management of navigation actions, route popping, and progress visibility
 * through [StateFlow] objects.
 *
 * This class maintains the current navigation actions and provides functionality for enabling/disabling
 * the progress indicator and navigating between screens.
 */
class NavigatorImpl : Navigator {

    // Private MutableStateFlows to hold internal navigation and progress states
    private val _alertActions: MutableStateFlow<Alert?> = MutableStateFlow(value = null)
    private val _navActions: MutableStateFlow<NavigationAction?> = MutableStateFlow(value = null)
    private val _popRouteActions: MutableStateFlow<String?> = MutableStateFlow(value = null)
    private val _shouldShowProgressAction: MutableStateFlow<Boolean> = MutableStateFlow(value = false)

    /**
     * A public immutable [StateFlow] that exposes the [Alert] action to be executed.
     *
     * This is used by other parts of the app to observe alerts and react accordingly.
     */
    override val alertActions: StateFlow<Alert?> = _alertActions.asStateFlow()

    /**
     * A public immutable [StateFlow] that exposes the current navigation action to be executed.
     *
     * This is used by other parts of the app to observe navigation actions and react accordingly.
     */
    override val navActions: StateFlow<NavigationAction?> = _navActions.asStateFlow()

    /**
     * A public immutable [StateFlow] that exposes the current route to be popped from the navigation stack.
     *
     * Other components in the app can listen to this flow to know when to pop the current route from the stack.
     */
    override val popRouteActions: StateFlow<String?> = _popRouteActions.asStateFlow()

    /**
     * A public immutable [StateFlow] that represents whether the progress indicator should be visible.
     *
     * Observing this [StateFlow] will allow components to show or hide a loading/progress indicator
     * based on whether it’s `true` or `false`.
     */
    override val shouldShowProgressAction: StateFlow<Boolean> = _shouldShowProgressAction.asStateFlow()

    /**
     * Enables the alert with the provided [Alert] action.
     *
     * This is typically used when starting / showing a alert.
     */
    override fun alert(alertAction: Alert?) {
        _alertActions.update { alertAction }
    }

    /**
     * Enables the progress indicator by updating the internal state to `true`.
     *
     * This is typically used when starting a long-running operation like a network request or data loading.
     */
    override fun enableProgress() {
        _shouldShowProgressAction.update { true }
    }

    /**
     * Disables the progress indicator by updating the internal state to `false`.
     *
     * This is typically used once a long-running operation has completed.
     */
    override fun disableProgress() {
        _shouldShowProgressAction.update { false }
    }

    /**
     * Updates the current navigation action state, triggering a navigation event.
     *
     * @param navigationAction The new navigation action to be executed. This could include the destination
     *        and any parameters associated with it.
     */
    override fun navigate(navigationAction: NavigationAction?) {
        _navActions.update { navigationAction }
    }

    /**
     * Updates the current route to be popped from the navigation stack.
     *
     * @param popRouteAction The route that should be popped from the stack. If `null`, no pop action will occur.
     */
    override fun pop(popRouteAction: String?) {
        _popRouteActions.update { popRouteAction }
    }
}

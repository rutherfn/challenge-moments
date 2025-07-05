package com.nicholas.rutherford.moments

import androidx.navigation.NavType
import androidx.navigation.navArgument

/**
 * A central place for defining constants used across the application.
 * This class houses different constant values related to navigation, string resources, and arguments.
 */
object Constants {

    /**
     * Contains constant keys used for navigation routes and parameters.
     * These are used in the navigation graph and when passing data between screens.
     */
    object NavigationKeys {
        /**
         * The route key used to navigate to the "Create/Edit Moment" screen.
         */
        const val CREATE_EDIT_MOMENT_ROUTE = "create_edit_moment_screen"

        /**
         * The key for the "title" parameter in the "Create/Edit Moment" screen.
         */
        const val PARAM_TITLE = "title"

        /**
         * The key for the "categoryId" parameter in the "Create/Edit Moment" screen.
         */
        const val PARAM_CATEGORY_ID = "categoryId"
    }

    /**
     * Contains constant string resource keys used across the app.
     * These constants are referenced to fetch the actual string resources
     * (like titles, descriptions, button text) from the `strings.xml` file.
     */
    object StringIds {
        /**
         * The key for the app's name.
         */
        const val APP_NAME = "app_name"

        /**
         * The key for the text shown when creating a moment.
         */
        const val ARE_YOU_SURE_YOU_WANT_TO_CREATE_THIS_MOMENT = "are_you_sure_you_want_to_create_this_moment"

        /**
         * The key for the text shown when deleting a moment.
         */
        const val ARE_YOU_SURE_YOU_WANT_TO_DELETE_THIS_MOMENT = "are_you_sure_you_want_to_delete_this_moment"

        /**
         * The key for the text shown when editing a moment.
         */
        const val ARE_YOU_SURE_YOU_WANT_TO_EDIT_THIS_MOMENT = "are_you_sure_you_want_to_edit_this_moment"

        /**
         * The key for the text shown for creating a moment.
         */
        const val CREATE_MOMENT = "create_moment"

        /**
         * The key for the text shown when creating a moment.
         */
        const val CREATING_MOMENT = "creating_moment"

        /**
         * The key for the text shown when editing a moment.
         */
        const val EDITING_MOMENT = "editing_moment"

        /**
         * The key for the text shown when deleting a moment.
         */
        const val DELETE_MOMENT = "delete_moment"

        /**
         * The key for the text shown when deleting a moment.
         */
        const val DELETING_MOMENT = "deleting_moment"

        /**
         * The key for the text shown when editing a moment.
         */
        const val EDIT_MOMENT = "edit_moment"

        /**
         * The key for the text representing the title of a moment.
         */
        const val MOMENT_TITLE = "moment_title"

        /**
         * The key for the text shown when there are no moments available.
         */
        const val NO_MOMENTS_YET = "no_moments_yet"

        /**
         * The key for the prompt asking the user to select a category.
         */
        const val SELECT_CATEGORY = "select_category"

        /**
         * The key for the prompt text shown for creating the first moment.
         */
        const val TAP_TO_CREATE_FIRST_MOMENT = "tap_to_create_first_moment"

        /**
         * The key for the alert title shown when no title is entered.
         */
        const val NO_TITLE_ENTERED_ALERT_TITLE = "no_title_entered_alert_title"

        /**
         * The key for the alert description shown when no title is entered.
         */
        const val NO_TITLE_ENTERED_ALERT_DESCRIPTION = "no_title_entered_alert_description"

        /**
         * The key for the button text on the alert when no title is entered.
         */
        const val NO_TITLE_ENTERED_ALERT_CONFIRM_BUTTON = "no_title_entered_alert_confirm_button"

        /**
         * The key for the text shown for no.
         */
        const val NO = "no"

        /**
         * The key for the alert title shown when no category is selected.
         */
        const val NO_CATEGORY_SELECTED_ALERT_TITLE = "no_category_selected_alert_title"

        /**
         * The key for the alert description shown when no category is selected.
         */
        const val NO_CATEGORY_SELECTED_ALERT_DESCRIPTION = "no_category_selected_alert_description"

        /**
         * The key for the button text on the alert when no category is selected.
         */
        const val NO_CATEGORY_SELECTED_ALERT_CONFIRM_BUTTON = "no_category_selected_alert_confirm_button"

        /**
         * The key for the text shown for yes.
         */
        const val YES = "yes"
    }

    /**
     * Contains constant definitions for navigation arguments required by specific screens.
     * These constants are used when navigating between screens and passing data along.
     */
    object NavigationArguments {

        /**
         * List of [NavArgument] definitions for the "Create/Edit Moment" screen.
         * This list specifies the parameters that need to be passed when navigating to this screen.
         *
         * Arguments:
         * - "title": The title of the moment (nullable).
         * - "categoryId": The category ID associated with the moment (non-nullable, default value is -1).
         */
        val CREATE_EDIT_MOMENT_ARGS = listOf(
            navArgument(NavigationKeys.PARAM_TITLE) {
                type = NavType.StringType
                nullable = true
                defaultValue = null
            },
            navArgument(NavigationKeys.PARAM_CATEGORY_ID) {
                type = NavType.IntType
                defaultValue = -1 // Sentinel value for "no category"
            }
        )
    }
}

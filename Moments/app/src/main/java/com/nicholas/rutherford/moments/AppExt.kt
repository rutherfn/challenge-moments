package com.nicholas.rutherford.moments

import android.content.Context
import timber.log.Timber

/**
 * Extension function for the [String] class that returns the first two characters of the string in uppercase.
 * If the string has less than two characters, the original string is returned as is.
 *
 * This function provides a simple way to extract and transform the first two characters of a string
 * to uppercase, often useful in scenarios such as abbreviations or initials.
 *
 * @return A string containing the first two characters in uppercase if the string has two or more characters,
 *         otherwise, it returns the original string.
 */
fun String.firstTwoChars(): String {
    return if (this.length >= 2) {
        this.substring(0, 2).uppercase()
    } else {
        this
    }
}


/**
 * Extension function for the [Context] class that retrieves a localized string
 * from the app's resources using a predefined string identifier (defined in [Constants.StringIds]).
 *
 * This function eliminates the need to directly use raw `R.string` references in the ViewModel or other
 * components by utilizing predefined string keys from the [Constants.StringIds] object. It ensures
 * that only valid string keys are used and provides a centralized location for managing string resources.
 *
 * @param stringId A string identifier (from [Constants.StringIds]) that corresponds to a specific string resource.
 *                 The stringId is mapped to the appropriate string resource from `strings.xml`.
 *
 * @return The localized string associated with the provided [stringId].
 *
 * @throws IllegalArgumentException If the provided [stringId] does not match any of the defined keys in [Constants.StringIds].
 *                                  This prevents accidental typos and ensures that only valid string resources are used.
 *                                  Additionally, a [Timber] log will be generated for this exception to assist with debugging.
 */
fun Context.getStringResource(stringId: String): String {
    return when (stringId) {
        Constants.StringIds.APP_NAME -> getString(R.string.app_name)
        Constants.StringIds.ARE_YOU_SURE_YOU_WANT_TO_CREATE_THIS_MOMENT -> getString(R.string.are_you_sure_you_want_to_create_this_moment)
        Constants.StringIds.ARE_YOU_SURE_YOU_WANT_TO_EDIT_THIS_MOMENT -> getString(R.string.are_you_sure_you_want_to_edit_this_moment)
        Constants.StringIds.ARE_YOU_SURE_YOU_WANT_TO_DELETE_THIS_MOMENT -> getString(R.string.are_you_sure_you_want_to_delete_this_moment)
        Constants.StringIds.CREATE_MOMENT -> getString(R.string.create_moment)
        Constants.StringIds.CREATING_MOMENT -> getString(R.string.creating_moment)
        Constants.StringIds.DELETE_MOMENT -> getString(R.string.delete_moment)
        Constants.StringIds.DELETING_MOMENT -> getString(R.string.deleting_moment)
        Constants.StringIds.EDITING_MOMENT -> getString(R.string.editing_moment)
        Constants.StringIds.EDIT_MOMENT -> getString(R.string.edit_moment)
        Constants.StringIds.MOMENT_TITLE -> getString(R.string.moment_title)
        Constants.StringIds.NO_MOMENTS_YET -> getString(R.string.no_moments_yet)
        Constants.StringIds.SELECT_CATEGORY -> getString(R.string.select_category)
        Constants.StringIds.TAP_TO_CREATE_FIRST_MOMENT -> getString(R.string.tap_to_create_first_moment)
        Constants.StringIds.NO -> getString(R.string.no)
        Constants.StringIds.NO_TITLE_ENTERED_ALERT_TITLE -> getString(R.string.no_title_entered_alert_title)
        Constants.StringIds.NO_TITLE_ENTERED_ALERT_DESCRIPTION -> getString(R.string.no_title_entered_alert_description)
        Constants.StringIds.NO_TITLE_ENTERED_ALERT_CONFIRM_BUTTON -> getString(R.string.no_title_entered_alert_confirm_button)
        Constants.StringIds.NO_CATEGORY_SELECTED_ALERT_TITLE -> getString(R.string.no_category_selected_alert_title)
        Constants.StringIds.NO_CATEGORY_SELECTED_ALERT_DESCRIPTION -> getString(R.string.no_category_selected_alert_description)
        Constants.StringIds.NO_CATEGORY_SELECTED_ALERT_CONFIRM_BUTTON -> getString(R.string.no_category_selected_alert_confirm_button)
        Constants.StringIds.YES -> getString(R.string.yes)
        else -> {
            Timber.e("Unknown string ID: $stringId")
            throw IllegalArgumentException("Unknown string ID: $stringId")
        }
    }
}




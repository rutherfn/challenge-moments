package com.nicholas.rutherford.moments.navigation

/**
 * Represents a button in an alert dialog. This button can either be a "Confirm" or "Dismiss" button.
 * It contains the button's text and an optional click listener to trigger specific actions when clicked.
 *
 * This data class is used to define buttons within an alert dialog. You can customize the button text and
 * provide a callback function to be executed when the button is clicked.
 *
 * @param buttonText The text to be displayed on the button (e.g., "OK", "Cancel", "Confirm").
 * @param onButtonClicked An optional callback function that is executed when the button is clicked.
 *                        This allows you to define what happens when the user interacts with the button.
 */
data class AlertConfirmAndDismissButton(
    val buttonText: String,
    val onButtonClicked: (() -> Unit)? = null
)

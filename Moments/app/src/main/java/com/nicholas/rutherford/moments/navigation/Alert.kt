package com.nicholas.rutherford.moments.navigation

/**
 * Represents an alert dialog that can be displayed to the user. The alert contains various components
 * such as a title, description, and buttons for user interaction.
 *
 * This data class provides the structure for configuring an alert dialog with:
 * - A title
 * - An optional description
 * - Optional buttons for user interaction (Confirm and Dismiss buttons)
 * - A callback function that gets triggered when the alert is dismissed
 *
 * @param title The title of the alert dialog. This will be displayed at the top of the dialog.
 * @param onDismissClicked An optional callback that will be triggered when the alert is dismissed.
 *                         This can be used to handle dismiss actions (e.g., closing the dialog).
 * @param confirmButton An optional [AlertConfirmAndDismissButton] that represents the "Confirm" button.
 *                      The button contains text and an optional click listener.
 * @param dismissButton An optional [AlertConfirmAndDismissButton] that represents the "Dismiss" button.
 *                      Similar to the confirm button, it contains text and an optional click listener.
 * @param description An optional description text that provides additional details to the user within the alert.
 */
data class Alert(
    val title: String,
    val onDismissClicked: (() -> Unit)? = null,
    val confirmButton: AlertConfirmAndDismissButton? = null,
    val dismissButton: AlertConfirmAndDismissButton? = null,
    val description: String? = null
)

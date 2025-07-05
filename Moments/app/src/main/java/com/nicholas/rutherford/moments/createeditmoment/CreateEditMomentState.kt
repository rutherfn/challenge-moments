package com.nicholas.rutherford.moments.createeditmoment

import com.nicholas.rutherford.moments.data.CategoryTag

/**
 * Data class that represents the state for the Create/Edit Moment screen.
 * Holds the data and UI states necessary for creating or editing a moment.
 *
 * @param createButtonEnabled Boolean flag indicating whether the "Create" button is enabled.
 * @param editButtonEnabled Boolean flag indicating whether the "Edit" button is enabled.
 * @param title The title of the moment, which can be empty when creating a new moment.
 * @param categoryTag The category tag associated with the moment, can be null if not yet selected.
 */
data class CreateEditMomentState(
    val createButtonEnabled: Boolean = false,  // Indicates if the "Create" button is enabled
    val editButtonEnabled: Boolean = false,    // Indicates if the "Edit" button is enabled
    val title: String = "",                    // The title of the moment, can be empty
    val categoryTag: CategoryTag? = null       // The category of the moment, nullable if not yet selected
)
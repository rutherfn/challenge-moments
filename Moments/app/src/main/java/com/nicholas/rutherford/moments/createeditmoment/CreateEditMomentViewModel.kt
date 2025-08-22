package com.nicholas.rutherford.moments.createeditmoment

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import com.nicholas.rutherford.moments.BaseViewModel
import com.nicholas.rutherford.moments.Constants
import com.nicholas.rutherford.moments.data.CategoryTag
import com.nicholas.rutherford.moments.getStringResource
import com.nicholas.rutherford.moments.navigation.Alert
import com.nicholas.rutherford.moments.navigation.AlertConfirmAndDismissButton
import com.nicholas.rutherford.moments.navigation.NavigationDestinations
import com.nicholas.rutherford.moments.navigation.Navigator
import com.nicholas.rutherford.moments.repository.MomentRepository
import com.nicholas.rutherford.moments.room.MomentEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel that manages the logic for creating, editing, and deleting moments.
 * It holds and updates the UI state and performs the necessary operations
 * on the [MomentRepository] for persistence.
 *
 * @param savedStateHandle A handle to retrieve saved state from navigation.
 * @param momentRepository A repository to interact with the moment data source (e.g., database).
 * @param scope A CoroutineScope used for launching background tasks.
 * @param navigator A navigator used for managing screen navigation.
 */
class CreateEditMomentViewModel(
    savedStateHandle: SavedStateHandle,
    private val application: Application,
    private val momentRepository: MomentRepository,
    private val scope: CoroutineScope,
    private val navigator: Navigator
) : BaseViewModel() {

    // Holds the moment title and category tag
    private var title: String = ""
    private var categoryTag: CategoryTag? = null

    // MutableStateFlow that holds the current UI state
    private val _state = MutableStateFlow(CreateEditMomentState())
    val state = _state.asStateFlow()

    // Parameters passed through navigation
    internal val titleParam: String = savedStateHandle.get<String>("title") ?: ""
    internal val categoryIdParam: Int = savedStateHandle.get<Int>("categoryId") ?: -1

    init { checkForParams() }

    /**
     * Checks if parameters (title and category) were passed in the navigation and not set to -1 or empty.
     * Then we will update the state accordingly.
     */
    internal fun checkForParams() {
        if (titleParam.isNotEmpty() && categoryIdParam != -1) {
            _state.update { state ->
                state.copy(
                    title = titleParam,
                    categoryTag = CategoryTag.fromTypeId(id = categoryIdParam),
                    editButtonEnabled = buildEditButtonEnabled()
                )
            }
        }
    }

    /**
     * Handles the back button press in the toolbar, navigating back to the home screen.
     */
    fun onToolbarBackButton() = navigator.pop(popRouteAction = NavigationDestinations.HOME_SCREEN)

    /**
     * Determines if the "Edit" button should be enabled based on title and category changes.
     */
    internal fun buildEditButtonEnabled(): Boolean {
        val hasTitleChanged = title.isNotEmpty() && title != titleParam
        val hasCategoryChanged = categoryTag != null && categoryTag != CategoryTag.fromTypeId(categoryIdParam)
        return hasTitleChanged || hasCategoryChanged
    }

    /**
     * Determines if the "Create" button should be enabled based on the title and category selection.
     */
    internal fun buildCreateButtonEnabled(): Boolean {
        return title.isNotBlank() && categoryTag != null
    }

    /**
     * Builds and returns an alert for when the user hasn't entered a title.
     * This alert notifies the user to enter a title for the moment.
     *
     * @return an [Alert] object configured with:
     *         - A title, retrieved from string resource with ID [Constants.StringIds.NO_TITLE_ENTERED_ALERT_TITLE],
     *         - A description, retrieved from string resource with ID [Constants.StringIds.NO_TITLE_ENTERED_ALERT_DESCRIPTION],
     *         - A confirm button with text, retrieved from string resource with ID [Constants.StringIds.NO_TITLE_ENTERED_ALERT_CONFIRM_BUTTON].
     */
    internal fun buildNoTitleEnteredAlert(): Alert {
        return Alert(
            title = application.getStringResource(Constants.StringIds.NO_TITLE_ENTERED_ALERT_TITLE),
            description = application.getStringResource(Constants.StringIds.NO_TITLE_ENTERED_ALERT_DESCRIPTION),
            confirmButton = AlertConfirmAndDismissButton(
                buttonText = application.getStringResource(Constants.StringIds.NO_TITLE_ENTERED_ALERT_CONFIRM_BUTTON)
            )
        )
    }

    /**
     * Builds and returns an alert for when the user hasn't selected a category.
     * This alert notifies the user to select a category for the moment.
     *
     * @return an [Alert] object configured with:
     *         - A title, retrieved from string resource with ID [Constants.StringIds.NO_CATEGORY_SELECTED_ALERT_TITLE],
     *         - A description, retrieved from string resource with ID [Constants.StringIds.NO_CATEGORY_SELECTED_ALERT_DESCRIPTION],
     *         - A confirm button with text, retrieved from string resource with ID [Constants.StringIds.NO_CATEGORY_SELECTED_ALERT_CONFIRM_BUTTON].
     */
    internal fun buildNoCategorySelectedAlert(): Alert {
        return Alert(
            title = application.getStringResource(Constants.StringIds.NO_CATEGORY_SELECTED_ALERT_TITLE),
            description = application.getStringResource(Constants.StringIds.NO_CATEGORY_SELECTED_ALERT_DESCRIPTION),
            confirmButton = AlertConfirmAndDismissButton(
                buttonText = application.getStringResource(Constants.StringIds.NO_CATEGORY_SELECTED_ALERT_CONFIRM_BUTTON)
            )
        )
    }

    /**
     * Builds and returns an alert that asks the user for confirmation before creating a new moment.
     * This alert will notify the user that they are about to create a new moment, and it will
     * ask if they are sure about this action.
     *
     * The alert contains:
     * - A title that notifies the user that they are creating a new moment.
     * - A description that further asks for confirmation to create the moment.
     * - A confirmation button that triggers the creation process if the user agrees.
     * - A dismiss button that cancels the creation process if the user disagrees.
     *
     * @return An [Alert] object containing the title, description, and action buttons for user confirmation.
     */
    internal fun buildAreYouSureYouWantToCreateThisMomentAlert(): Alert {
        return Alert(
            title = application.getStringResource(Constants.StringIds.CREATING_MOMENT),
            description = application.getStringResource(Constants.StringIds.ARE_YOU_SURE_YOU_WANT_TO_CREATE_THIS_MOMENT),
            confirmButton = AlertConfirmAndDismissButton(
                buttonText = application.getStringResource(Constants.StringIds.YES),
                onButtonClicked = { onConfirmButtonClicked(isCreateAction = true) }
            ),
            dismissButton = AlertConfirmAndDismissButton(
                buttonText = application.getStringResource(Constants.StringIds.NO)
            )
        )
    }

    /**
     * Builds and returns an alert that asks the user for confirmation before editing an existing moment.
     * This alert will notify the user that they are about to edit a moment and ask if they are sure
     * about making this change.
     *
     * The alert contains:
     * - A title indicating that the user is about to edit a moment.
     * - A description that asks for confirmation to edit the moment.
     * - A confirmation button that triggers the edit process if the user agrees.
     * - A dismiss button that cancels the edit process if the user disagrees.
     *
     * @return An [Alert] object containing the title, description, and action buttons for user confirmation.
     */
    internal fun buildAreYouSureYouWantToEditThisMomentAlert(): Alert {
        return Alert(
            title = application.getStringResource(Constants.StringIds.EDITING_MOMENT),
            description = application.getStringResource(Constants.StringIds.ARE_YOU_SURE_YOU_WANT_TO_EDIT_THIS_MOMENT),
            confirmButton = AlertConfirmAndDismissButton(
                buttonText = application.getStringResource(Constants.StringIds.YES),
                onButtonClicked = { onConfirmButtonClicked(isCreateAction = false) }
            ),
            dismissButton = AlertConfirmAndDismissButton(
                buttonText = application.getStringResource(Constants.StringIds.NO)
            )
        )
    }

    /**
     * Builds and returns an alert that asks the user for confirmation before deleting a moment.
     * This alert will notify the user that they are about to delete a moment and ask if they are sure
     * about this action.
     *
     * The alert contains:
     * - A title indicating that the user is about to delete a moment.
     * - A description asking for confirmation to delete the moment.
     * - A confirmation button that triggers the delete process if the user agrees.
     *
     * @return An [Alert] object containing the title, description, and action buttons for user confirmation.
     */
    internal fun buildAreYouSureYouWantToDeleteThisMomentAlert(): Alert {
        return Alert(
            title = application.getStringResource(Constants.StringIds.DELETING_MOMENT),
            description = application.getStringResource(Constants.StringIds.ARE_YOU_SURE_YOU_WANT_TO_DELETE_THIS_MOMENT),
            confirmButton = AlertConfirmAndDismissButton(
                buttonText = application.getStringResource(Constants.StringIds.YES),
                onButtonClicked = { onDeleteYesButtonClicked() }
            ),
            dismissButton = AlertConfirmAndDismissButton(
                buttonText = application.getStringResource(Constants.StringIds.NO)
            )
        )
    }

    /**
     * Builds and returns an error if the user does not enter a title or select a category.
     * It will check first if the title is empty and if its not it will return the no category tag selected alert.
     *
     * @return An [Alert] object containing either ][buildNoTitleEnteredAlert] or [buildNoCategorySelectedAlert]
     */
    fun buildCreateOrEditMomentErrorAlert(title: String): Alert {
        return if (title.isEmpty()) {
            buildNoTitleEnteredAlert()
        } else {
            buildNoCategorySelectedAlert()
        }
    }

    /**
     * Updates the title when the user changes it in the UI.
     */
    fun onTitleValueChanged(title: String) {
        this.title = title
        _state.update { state ->
            state.copy(
                title = title,
                createButtonEnabled = buildCreateButtonEnabled(),
                editButtonEnabled = buildEditButtonEnabled()
            )
        }
    }

    /**
     * Updates the selected category tag when the user selects a category.
     */
    fun onCategorySelected(categoryTag: CategoryTag) {
        this.categoryTag = categoryTag
        _state.update { state ->
            state.copy(
                categoryTag = categoryTag,
                createButtonEnabled = buildCreateButtonEnabled(),
                editButtonEnabled = buildEditButtonEnabled()
            )
        }
    }

    /**
     * Clears the state, resetting title and category, and disabling buttons.
     */
    internal fun clearState() {
        title = ""
        categoryTag = null
        _state.update { state ->
            state.copy(
                createButtonEnabled = false,
                editButtonEnabled = false,
                title = "",
                categoryTag = null
            )
        }
    }

    /**
     * Clears the state and pops the navigation stack, going back to the home screen.
     */
    internal fun clearStateAndPopStack() {
        clearState()
        navigator.pop(popRouteAction = NavigationDestinations.HOME_SCREEN)
    }
    /**
     * Triggered when the "Create" button is clicked. This function builds and shows an alert
     * asking the user to confirm if they want to create a new moment.
     *
     * The alert will:
     * - Display a title indicating the creation of a new moment.
     * - Display a description asking for confirmation to create the moment.
     * - Provide confirmation and cancel buttons for the user to proceed or dismiss the action.
     *
     * After the user confirms, the moment creation process will proceed.
     */
    fun onCreateButtonClicked() =
        navigator.alert(alertAction = buildAreYouSureYouWantToCreateThisMomentAlert())

    /**
     * Triggered when the "Edit" button is clicked. This function builds and shows an alert
     * asking the user to confirm if they want to edit the selected moment.
     *
     * The alert will:
     * - Display a title indicating the editing of an existing moment.
     * - Display a description asking for confirmation to edit the moment.
     * - Provide confirmation and cancel buttons for the user to proceed or dismiss the action.
     *
     * After the user confirms, the moment editing process will proceed.
     */
    fun onEditButtonClicked() =
        navigator.alert(alertAction = buildAreYouSureYouWantToEditThisMomentAlert())

    /**
     * Triggered when the "Delete" button is clicked. This function builds and shows an alert
     * asking the user to confirm if they want to delete the selected moment.
     *
     * The alert will:
     * - Display a title indicating the deletion of a moment.
     * - Display a description asking for confirmation to delete the moment.
     * - Provide confirmation and cancel buttons for the user to proceed or dismiss the action.
     *
     * After the user confirms, the moment deletion process will proceed.
     */
    fun onDeleteButtonClicked() =
        navigator.alert(alertAction = buildAreYouSureYouWantToDeleteThisMomentAlert())

    /**
     * Handles the action when the "Yes" button is clicked, from the alert.
     * Inserts a new moment or edits an existing one in the repository, depending on the provided action type(where your coming from either edit or create option)
     *
     * @param isCreateAction A flag indicating whether the action is creating or editing a moment.
     *                       If true, it performs a create action, else it performs an edit action.
     * @param title The title [String] of the moment to be inserted or edited.
     * @param categoryTag The category tag [CategoryTag] of the moment to be inserted or edited.
     */
    suspend fun insertOrEditMoment(isCreateAction: Boolean, title: String, categoryTag: CategoryTag) {
        if (isCreateAction) {
            momentRepository.insertMoment(
                moment = MomentEntity(
                    title = title,
                    categoryTag = categoryTag
                )
            )
        } else {
            momentRepository.editMoment(
                currentMomentTitle = titleParam,
                newMoment = MomentEntity(
                    title = title,
                    categoryTag = categoryTag
                )
            )
        }
    }

    /**
     * Handles the action when the "Yes" button is clicked, from the alert.
     * Inserts a new moment or edits an existing one in the repository, depending on the provided action type.
     *
     * @param isCreateAction A flag indicating whether the action is creating or editing a moment.
     *                       If true, it performs a create action, else it performs an edit action.
     */
    fun onConfirmButtonClicked(isCreateAction: Boolean) {
        scope.launch {
            navigator.enableProgress()

            val finalTitle = title.ifEmpty { titleParam }
            val finalCategoryTag = categoryTag ?: if (categoryIdParam == -1) {
                null
            } else {
                CategoryTag.fromTypeId(id = categoryIdParam)
            }

            if (finalTitle.isNotEmpty() && finalCategoryTag != null) {
                insertOrEditMoment(
                    isCreateAction = isCreateAction,
                    title = finalTitle,
                    categoryTag = finalCategoryTag
                )
                clearStateAndPopStack()
            } else {
                navigator.disableProgress()
                navigator.alert(alertAction = buildCreateOrEditMomentErrorAlert(title = finalTitle))
            }
        }
    }


    /**
     * Handles the action when the "Delete" button is clicked.
     * Deletes the moment from the repository.
     */
    fun onDeleteYesButtonClicked() {
        scope.launch {
            navigator.enableProgress()

            momentRepository.deleteMoment(title = titleParam)
            clearStateAndPopStack()
        }
    }
}

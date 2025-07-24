package com.nicholas.rutherford.moments.createeditmoment

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nicholas.rutherford.moments.R
import com.nicholas.rutherford.moments.data.CategoryTag
import com.nicholas.rutherford.moments.data.CategoryTag.Other.all
import com.nicholas.rutherford.moments.testtags.CreateEditMomentScreenTestTags

/**
 * Composable that displays the create or edit moment screen.
 * Handles the back button press, updates the UI based on whether the screen is in "editing" mode,
 * and provides handlers for creating, editing, or deleting a moment.
 *
 * @param state The state holding the moment's current data (e.g., title, selected category).
 * @param isEditing Boolean indicating if the screen is in edit mode.
 * @param onToolbarBackButton Callback invoked when the back button is pressed.
 * @param onTitleValueChanged Callback invoked when the title text is changed.
 * @param onCategorySelected Callback invoked when a category is selected.
 * @param onCreateButtonClicked Callback invoked when the "Create" button is clicked.
 * @param onEditButtonClicked Callback invoked when the "Edit" button is clicked.
 * @param onDeleteButtonClicked Callback invoked when the "Delete" button is clicked.
 */
@Composable
fun CreateEditMomentScreen(
    state: CreateEditMomentState,
    isEditing: Boolean,
    onToolbarBackButton: () -> Unit,
    onTitleValueChanged: (String) -> Unit,
    onCategorySelected: (CategoryTag) -> Unit,
    onCreateButtonClicked: () -> Unit,
    onEditButtonClicked: () -> Unit,
    onDeleteButtonClicked: () -> Unit
) {
    BackHandler { onToolbarBackButton.invoke() }
    CreateEditMomentContent(
        state = state,
        isEditing = isEditing,
        onTitleValueChanged = onTitleValueChanged,
        onCategorySelected = onCategorySelected,
        onCreateButtonClicked = onCreateButtonClicked,
        onEditButtonClicked = onEditButtonClicked,
        onDeleteButtonClicked = onDeleteButtonClicked
    )
}

/**
 * Composable that displays the form content for creating or editing a moment.
 * It includes the title input, category selection dropdown, and buttons for creating, editing, or deleting the moment.
 *
 * @param state The state holding the current title and category data.
 * @param isEditing Boolean indicating if the screen is in edit mode.
 * @param onTitleValueChanged Callback invoked when the title text is changed.
 * @param onCategorySelected Callback invoked when a category is selected.
 * @param onCreateButtonClicked Callback invoked when the "Create" button is clicked.
 * @param onEditButtonClicked Callback invoked when the "Edit" button is clicked.
 * @param onDeleteButtonClicked Callback invoked when the "Delete" button is clicked.
 */
@Composable
fun CreateEditMomentContent(
    state: CreateEditMomentState,
    isEditing: Boolean,
    onTitleValueChanged: (String) -> Unit,
    onCategorySelected: (CategoryTag) -> Unit,
    onCreateButtonClicked: () -> Unit,
    onEditButtonClicked: () -> Unit,
    onDeleteButtonClicked: () -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    var expanded by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = if (isEditing) {
                stringResource(id = R.string.edit_moment)
            } else {
                stringResource(id = R.string.create_moment)
            },
            modifier = Modifier.testTag(tag = CreateEditMomentScreenTestTags.CREATE_EDIT_MOMENT_TITLE),
            style = MaterialTheme.typography.headlineMedium,
        )

        OutlinedTextField(
            value = state.title,
            onValueChange = { title -> onTitleValueChanged.invoke(title) },
            label = { Text(text = stringResource(id = R.string.moment_title), modifier = Modifier.testTag(tag = CreateEditMomentScreenTestTags.MOMENT_TEXT_FIELD_TITLE)) },
            modifier = Modifier.fillMaxWidth().focusRequester(focusRequester).testTag(tag = CreateEditMomentScreenTestTags.MOMENT_TEXT_FIELD),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.TopStart)
        ) {
            OutlinedButton(
                onClick = {
                    //focusRequester.freeFocus()
                    expanded = true

                    println("get here testtest $expanded")
                          },
                modifier = Modifier.fillMaxWidth().testTag(tag = CreateEditMomentScreenTestTags.CATEGORY_TAG_BUTTON)
            ) {
                Text(
                    text = state.categoryTag?.title ?: stringResource(R.string.select_category),
                    modifier = Modifier.testTag(tag = CreateEditMomentScreenTestTags.CATEGORY_TAG_BUTTON_TEXT)
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                listOf(1,2,3).forEachIndexed { index, category ->
                    DropdownMenuItem(
                        enabled = true,
                        text = {
                            Text(
                                text = index.toString(),
                                modifier = Modifier.testTag(tag = CreateEditMomentScreenTestTags.dropDownMenuItem(index = index))
                            )
                               },
                        onClick = {
                            //focusRequester.freeFocus()
                           // onCategorySelected.invoke(category)
                            expanded = false
                        }
                    )
                }
            }
        }

        Button(
            onClick = {
                focusRequester.freeFocus()
                if (isEditing) {
                    onEditButtonClicked.invoke()
                } else {
                    onCreateButtonClicked.invoke()
                }
            },
            enabled = if (isEditing) {
                state.editButtonEnabled
            } else {
                state.createButtonEnabled
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = if (isEditing) {
                    stringResource(R.string.edit_moment)
                } else {
                    stringResource(R.string.create_moment)
                }
            )
        }

        if (isEditing) {
            Button(
                onClick = {
                    focusRequester.freeFocus()
                    onDeleteButtonClicked.invoke()
                          },
                enabled = true,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = MaterialTheme.colorScheme.onError
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.delete_moment))
            }
        }
    }
}

// Sample CreateEditMomentState for preview purposes
fun getSampleState(): CreateEditMomentState {
    return CreateEditMomentState(
        title = "Sample Moment Title",
        categoryTag = CategoryTag.Other,
        createButtonEnabled = true,
        editButtonEnabled = true
    )
}

@Preview(name = "Create Moment", showBackground = true)
@Composable
private fun CreateMomentPreview() {
    CreateEditMomentScreen(
        state = getSampleState(),
        isEditing = false,
        onToolbarBackButton = {},
        onTitleValueChanged = {},
        onCategorySelected = {},
        onCreateButtonClicked = {},
        onEditButtonClicked = {},
        onDeleteButtonClicked = {}
    )
}

@Preview(name = "Edit Moment", showBackground = true)
@Composable
private fun EditMomentPreview() {
    CreateEditMomentScreen(
        state = getSampleState(),
        isEditing = true,
        onToolbarBackButton = {},
        onTitleValueChanged = {},
        onCategorySelected = {},
        onCreateButtonClicked = {},
        onEditButtonClicked = {},
        onDeleteButtonClicked = {}
    )
}

@Preview(name = "Create Moment Content", showBackground = true)
@Composable
private fun CreateMomentContentPreview() {
    CreateEditMomentContent(
        state = getSampleState(),
        isEditing = false,
        onTitleValueChanged = {},
        onCategorySelected = {},
        onCreateButtonClicked = {},
        onEditButtonClicked = {},
        onDeleteButtonClicked = {}
    )
}

@Preview(name = "Edit Moment Content", showBackground = true)
@Composable
private fun EditMomentContentPreview() {
    CreateEditMomentContent(
        state = getSampleState(),
        isEditing = true,
        onTitleValueChanged = {},
        onCategorySelected = {},
        onCreateButtonClicked = {},
        onEditButtonClicked = {},
        onDeleteButtonClicked = {}
    )
}

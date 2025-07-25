package com.nicholas.rutherford.moments.composeext

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import com.nicholas.rutherford.moments.navigation.AlertConfirmAndDismissButton
import com.nicholas.rutherford.moments.ui.theme.Blue40
import java.util.Locale

/**
 * Default [AlertDialog] with given params to build alerts used in content
 *
 * @param title sets the [Text] for the [AlertDialog]
 * @param onDismissClicked optional param triggers whenever the user attempts to dismiss the [AlertDialog]
 * @param confirmButton optional param that by default is set to null. If its set to true, will set [AlertDialog] confirmButton properties
 * @param dismissButton optional param that by default is set to null. If its set to true, will set [AlertDialog] dismissButton properties
 * @param description optional param that sets the [Text] of the [AlertDialog]
 */
@Composable
fun AlertDialog(
    title: String,
    onDismissClicked: (() -> Unit)? = null,
    confirmButton: AlertConfirmAndDismissButton? = null,
    dismissButton: AlertConfirmAndDismissButton? = null,
    description: String? = null
) {
    AlertDialog(
        onDismissRequest = { onDismissClicked?.invoke() },
        confirmButton = {
            confirmButton?.let { button ->
                TextButton(
                    onClick = { button.onButtonClicked?.invoke() },
                    content = { Text(text = button.buttonText.uppercase(Locale.ROOT)) },
                    colors = ButtonDefaults.textButtonColors(contentColor = Blue40)
                )
            }
        },
        dismissButton = {
            dismissButton?.let { button ->
                TextButton(
                    onClick = { button.onButtonClicked?.invoke() },
                    content = { Text(text = button.buttonText.uppercase(Locale.ROOT)) },
                    colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.error)
                )
            }
        },
        title = { Text(text = title) },
        text = { description?.let { text -> Text(text = text) } },
        modifier = Modifier.padding(32.dp),
        shape = RoundedCornerShape(25.dp)
    )
}

@Composable
@Preview
fun AlertDialogPreview() {
    AlertDialog(
        title = "Title 1",
        onDismissClicked = {},
        confirmButton = AlertConfirmAndDismissButton(
            buttonText = "Confirm"
        ),
        dismissButton = AlertConfirmAndDismissButton(
            buttonText = "Dismiss"
        ),
        description = "Here is a set description for the alert"
    )
}


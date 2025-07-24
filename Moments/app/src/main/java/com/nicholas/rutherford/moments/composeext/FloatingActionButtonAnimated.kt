package com.nicholas.rutherford.moments.composeext

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nicholas.rutherford.moments.testtags.FloatingActionButtonTestTags
import com.nicholas.rutherford.moments.ui.theme.Blue40

/**
 * Composable that displays an animated [FloatingActionButton] with a scaling effect
 * when pressed. The scale animation runs with a duration of 100ms, and upon completion,
 * a callback is triggered if the button is pressed.
 *
 * @param isPressed A boolean value indicating if the button is pressed. When true,
 *        the button will scale down, and when false, it returns to its normal size.
 * @param onClick A lambda function to handle the button click event.
 * @param onAnimationEnd A lambda function that gets triggered when the scale animation finishes.
 * @param testTag An optional test tag for the FloatingActionButton.
 */
@Composable
fun FloatingActionButtonAnimated(
    isPressed: Boolean,
    onClick: () -> Unit,
    onAnimationEnd: () -> Unit,
    testTag: String? = null
) {
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = tween(durationMillis = 100),
        finishedListener = {
            if (isPressed) {
                onAnimationEnd()
            }
        }
    )

    FloatingActionButton(
        onClick = onClick,
        modifier = Modifier
            .testTag(tag = FloatingActionButtonTestTags.FLOATING_ACTION_BUTTON)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .size(72.dp),
        shape = RoundedCornerShape(50),
        containerColor = Blue40,
        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 8.dp)
    ) {
        Icon(Icons.Default.Add, contentDescription = "Add Note", tint = Color.White)
    }
}

@Preview
@Composable
private fun FloatingActionButtonAnimatedPreview() {
    FloatingActionButtonAnimated(
        isPressed = true,
        onClick = {},
        onAnimationEnd = {}
    )
}

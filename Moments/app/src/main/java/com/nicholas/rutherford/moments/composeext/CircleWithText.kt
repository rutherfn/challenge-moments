package com.nicholas.rutherford.moments.composeext

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nicholas.rutherford.moments.ui.theme.Blue40

/**
 * Displays a circle with centered text inside.
 *
 * @param text The text shown in the center of the circle.
 * @param modifier The modifier for the circle.
 * @param backgroundColor The color of the circle's background.
 * @param textColor The color of the text.
 * @param size The diameter of the circle.
 */
@Composable
fun CircleWithText(
    text: String,
    modifier: Modifier = Modifier, backgroundColor: Color =  Blue40,
    textColor: Color = Color.White,
    size: Dp = 46.dp
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(backgroundColor)
    ) {
        Text(
            text = text.take(2),
            color = textColor,
            maxLines = 1,
            overflow = TextOverflow.Clip,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun CircleWithTextPreview() {
    CircleWithText(text = "Hello")
}

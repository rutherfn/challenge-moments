package com.nicholas.rutherford.moments.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nicholas.rutherford.moments.R
import com.nicholas.rutherford.moments.composeext.CircleWithText
import com.nicholas.rutherford.moments.data.CategoryTag
import com.nicholas.rutherford.moments.firstTwoChars
import com.nicholas.rutherford.moments.room.MomentEntity
import com.nicholas.rutherford.moments.ui.theme.MomentsTheme

/**
 * Composable that displays the home screen, showing a list of moments or an empty state
 * if no moments are available.
 *
 * @param notes List of [MomentEntity] to display.
 * @param onItemClicked Callback that is triggered when an item is clicked, passing the title
 *        and category tag identifier of the clicked note.
 */
@Composable
fun HomeScreen(
    notes: List<MomentEntity>,
    onItemClicked: (title: String, categoryTag: Int) -> Unit
) {
    MomentsTheme {
        if (notes.isEmpty()) {
            HomeScreenEmptyState()
        } else {
            HomeScreenList(notes = notes, onItemClicked = onItemClicked)
        }
    }
}

/**
 * Composable that displays an empty state when there are no moments.
 *
 * This screen prompts the user to create a new moment.
 */
@Composable
fun HomeScreenEmptyState() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(R.string.no_moments_yet),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.tap_to_create_first_moment),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
            textAlign = TextAlign.Center
        )
    }
}


/**
 * Composable that displays a list of moments using a [LazyColumn].
 *
 * Each moment is displayed in a [Card] with the category's first two characters
 * shown in a circle next to the moment's title.
 *
 * @param notes List of [MomentEntity] to display in the list.
 * @param onItemClicked Callback that is triggered when an item is clicked, passing the
 *        title and category tag identifier of the clicked note.
 */
@Composable
fun HomeScreenList(
    notes: List<MomentEntity>,
    onItemClicked: (title: String, categoryTag: Int) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(notes.size) { index ->
            val note = notes[index]

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .clickable { onItemClicked.invoke(note.title, note.categoryTag.typeIdentifier) },
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircleWithText(note.categoryTag.title.firstTwoChars())

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(text = note.title)
                }
            }
        }

    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    // Mock data for preview
    val mockNotes = listOf(
        MomentEntity(title = "Family Reunion", categoryTag = CategoryTag.Family),
        MomentEntity(title = "Love at First Sight", categoryTag = CategoryTag.Love),
        MomentEntity(title = "Tech Innovations", categoryTag = CategoryTag.Tech)
    )

    val onItemClicked: (String, Int) -> Unit = { title, categoryTag -> println("Clicked on: $title with categoryTag: $categoryTag") }

    // HomeScreen composable with mock data and callback
    HomeScreen(notes = mockNotes, onItemClicked = onItemClicked)
}

package com.nicholas.rutherford.moments.ext

import androidx.compose.ui.test.ComposeTimeoutException
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasImeAction
import androidx.compose.ui.test.isFocused
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.text.input.ImeAction
import com.nicholas.rutherford.moments.robots.CreateMomentScreenRobot
import com.nicholas.rutherford.moments.robots.FloatingActionButtonRobot
import com.nicholas.rutherford.moments.robots.HomeScreenRobot
import com.nicholas.rutherford.moments.robots.TopAppBarRobot

fun ComposeTestRule.useHomeScreenRobot(block: HomeScreenRobot.() -> Unit) {
    HomeScreenRobot(this).apply(block)
}

fun ComposeTestRule.useTopAppBarRobot(block: TopAppBarRobot.() -> Unit) {
    TopAppBarRobot(this).apply(block)
}

fun ComposeTestRule.useFloatingActionButtonRobot(block: FloatingActionButtonRobot.() -> Unit) {
    FloatingActionButtonRobot(this).apply(block)
}

fun ComposeTestRule.useCreateMomentScreenRobot(block: CreateMomentScreenRobot.() -> Unit) {
    CreateMomentScreenRobot(this).apply(block)
}

/**
 * Attempts to close the soft keyboard by performing an IME action
 * on the currently focused component.
 *
 * This is a best-effort approach. It works if a component (like a TextField)
 * is focused and has an IME action (e.g., Done, Search) that dismisses the keyboard.
 */
@OptIn(ExperimentalTestApi::class) // For performImeAction if your version requires it
fun ComposeTestRule.closeKeyboardIfOpen() {
    try {
        // Find a node that is focused and has an IME action, then perform it.
        // This often corresponds to pressing the "Done" or "Search" button on the keyboard.
        onNode(isFocused() and hasImeAction(ImeAction.Done))
            .performImeAction()
        // Wait briefly for the keyboard to animate away if needed, though often not necessary
        // this.mainClock.advanceTimeBy(100) // Optional: if you face timing issues
    } catch (e: AssertionError) {
        // This can happen if no node is focused, or the focused node has no IME action,
        // or if multiple nodes match (which should be rare for 'isFocused').
        println("Could not close keyboard using performImeAction on a focused node: ${e.message}")
        // Consider alternative strategies if this fails, e.g., clicking a specific "Done" button
        // in your UI, or clicking on a known non-interactive area if that dismisses the keyboard.
    } catch (e: ComposeTimeoutException) {
        // Also catch timeout exceptions if the node is not found quickly
        println("Timeout while trying to close keyboard: ${e.message}")
    }
}

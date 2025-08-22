package com.nicholas.rutherford.moments

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.semantics.text
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput // Added this import

fun SemanticsNodeInteraction.shouldBeDisplayed() = this.assertIsDisplayed()

fun SemanticsNodeInteraction.shouldNotBeDisplayed() = this.assertIsNotDisplayed()

fun SemanticsNodeInteraction.shouldHaveText(value: String) = this.assertTextEquals(value)


fun SemanticsNodeInteraction.shouldHaveEditableText(expectedText: String) {
    val editableText = fetchSemanticsNode().config.getOrNull(SemanticsProperties.EditableText)
    val actualText = editableText?.text
    assert(actualText == expectedText) {
        // Simple assertion, you might want to use a more specific test assertion from your framework
        "Failed to assert that editable text is '$expectedText'. Actual text is '$actualText'."
    }
}

fun SemanticsNodeInteraction.clickOn() {
    this.performClick()
}

fun SemanticsNodeInteraction.typeText(textToType: String) {
    this.clickOn()
    this.performTextInput(textToType)
}

package com.nicholas.rutherford.moments.robots

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import com.nicholas.rutherford.moments.clickOn
import com.nicholas.rutherford.moments.shouldBeDisplayed
import com.nicholas.rutherford.moments.shouldNotBeDisplayed
import com.nicholas.rutherford.moments.testtags.FloatingActionButtonTestTags

class FloatingActionButtonRobot(composeTestRule: ComposeTestRule) {

    private val floatingActionButtonNode: SemanticsNodeInteraction =
        composeTestRule.onNodeWithTag(testTag = FloatingActionButtonTestTags.FLOATING_ACTION_BUTTON)

    fun verifyFloatingActionButtonIsDisplayed() = floatingActionButtonNode.shouldBeDisplayed()

    fun verifyFloatingActionButtonIsNotDisplayed() = floatingActionButtonNode.shouldNotBeDisplayed()

    fun clickOnFloatingActionButton() = floatingActionButtonNode.clickOn()
}
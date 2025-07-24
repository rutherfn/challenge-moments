package com.nicholas.rutherford.moments.robots

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import com.nicholas.rutherford.moments.testtags.HomeScreenTestTags
import com.nicholas.rutherford.moments.shouldBeDisplayed
import com.nicholas.rutherford.moments.shouldHaveText

class HomeScreenRobot(composeTestRule: ComposeTestRule) {

    private val emptyStateTitleNode: SemanticsNodeInteraction =
        composeTestRule.onNodeWithTag(testTag = HomeScreenTestTags.NO_MOMENTS_YET_TITLE)

    private val emptyStateDescriptionNode: SemanticsNodeInteraction =
        composeTestRule.onNodeWithTag(testTag = HomeScreenTestTags.TAP_TO_ADD_MOMENTS_DESCRIPTION)

    fun verifyEmptyStateTitleIsDisplayed() = emptyStateTitleNode.shouldBeDisplayed()

    fun verifyEmptyStateDescriptionIsDisplayed() = emptyStateDescriptionNode.shouldBeDisplayed()

    fun verifyEmptyStateTitleText(value: String) = emptyStateTitleNode.shouldHaveText(value = value)

    fun verifyEmptyStateDescriptionText(value: String) = emptyStateDescriptionNode.shouldHaveText(value = value)


}
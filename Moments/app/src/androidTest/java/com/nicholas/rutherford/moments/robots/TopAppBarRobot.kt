package com.nicholas.rutherford.moments.robots

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import com.nicholas.rutherford.moments.shouldBeDisplayed
import com.nicholas.rutherford.moments.shouldHaveText
import com.nicholas.rutherford.moments.shouldNotBeDisplayed
import com.nicholas.rutherford.moments.testtags.TopAppBarTestTags

class TopAppBarRobot(composeTestRule: ComposeTestRule) {

    private val topAppBarTitleNode: SemanticsNodeInteraction =
        composeTestRule.onNodeWithTag(testTag = TopAppBarTestTags.TOP_APP_BAR_TITLE)

    private val topAppBarBackIconButtonNode: SemanticsNodeInteraction =
        composeTestRule.onNodeWithTag(testTag = TopAppBarTestTags.TOP_APP_BAR_BACK_ICON_BUTTON)

    fun verifyTopAppBarTitleIsDisplayed() = topAppBarTitleNode.shouldBeDisplayed()

    fun verifyTopAppBarTitleText(value: String) = topAppBarTitleNode.shouldHaveText(value = value)

    fun verifyTopAppBarBackIconButtonIsDisplayed() = topAppBarBackIconButtonNode.shouldBeDisplayed()

    fun verifyTopAppBarBackIconButtonIsNotDisplayed() = topAppBarBackIconButtonNode.shouldNotBeDisplayed()
}
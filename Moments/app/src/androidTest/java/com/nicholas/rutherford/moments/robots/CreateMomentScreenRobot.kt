package com.nicholas.rutherford.moments.robots

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.nicholas.rutherford.moments.shouldBeDisplayed
import com.nicholas.rutherford.moments.shouldHaveEditableText
import com.nicholas.rutherford.moments.shouldHaveText
import com.nicholas.rutherford.moments.testtags.CreateEditMomentScreenTestTags
import com.nicholas.rutherford.moments.typeText

class CreateMomentScreenRobot(private val composeTestRule: ComposeTestRule) {

    private val createMomentTitleNode: SemanticsNodeInteraction =
        composeTestRule.onNodeWithTag(testTag = CreateEditMomentScreenTestTags.CREATE_EDIT_MOMENT_TITLE)

    private val momentTextFieldTitleNode: SemanticsNodeInteraction =
        composeTestRule.onNodeWithTag(testTag = CreateEditMomentScreenTestTags.MOMENT_TEXT_FIELD_TITLE, useUnmergedTree = true)

    private val momentTextFieldNode: SemanticsNodeInteraction =
        composeTestRule.onNodeWithTag(testTag = CreateEditMomentScreenTestTags.MOMENT_TEXT_FIELD)

    private val categoryTagButtonNode: SemanticsNodeInteraction =
        composeTestRule.onNodeWithTag(testTag = CreateEditMomentScreenTestTags.CATEGORY_TAG_BUTTON)

    private val categoryTagButtonTextNode: SemanticsNodeInteraction =
        composeTestRule.onNodeWithTag(testTag = CreateEditMomentScreenTestTags.CATEGORY_TAG_BUTTON_TEXT)

    fun verifyCreateMomentTitleIsDisplayed() = createMomentTitleNode.shouldBeDisplayed()

    fun verifyCreateMomentTitleText(value: String) = createMomentTitleNode.shouldHaveText(value = value)

    fun verifyMomentTextFieldTitleIsDisplayed() = momentTextFieldTitleNode.shouldBeDisplayed()

    fun verifyMomentTextFieldTitleText(value: String) = momentTextFieldTitleNode.shouldHaveText(value = value)

    fun verifyMomentTextFieldIsDisplayed() = momentTextFieldNode.shouldBeDisplayed()

    fun verifyMomentTextFieldText(value: String) = momentTextFieldNode.shouldHaveEditableText(value)

    fun verifyCategoryTagButtonIsDisplayed() = categoryTagButtonNode.shouldBeDisplayed()

    fun clickOnCategoryTagButton() = categoryTagButtonNode.performClick()

    fun verifyCategoryTagButtonTextIsDisplayed() = categoryTagButtonTextNode.shouldBeDisplayed()

    fun verifyCategoryTagButtonTextText(value: String) = categoryTagButtonTextNode.shouldHaveText(value = value)

    fun verifyDropDownMenuItemIsDisplayed(index: Int) {
        composeTestRule.onNodeWithTag(testTag = CreateEditMomentScreenTestTags.dropDownMenuItem(index = index)).shouldBeDisplayed()
    }

    fun verifyDropDownMenuItemText(index: Int, value: String) {
        composeTestRule.onNodeWithTag(testTag = CreateEditMomentScreenTestTags.dropDownMenuItem(index = index)).shouldHaveText(value = value)
    }

    fun typeMoment(textToType: String) = momentTextFieldNode.typeText(textToType)
}
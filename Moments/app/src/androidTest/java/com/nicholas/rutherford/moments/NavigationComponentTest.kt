package com.nicholas.rutherford.moments

import android.app.Application
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import com.nicholas.rutherford.moments.ext.useCreateMomentScreenRobot
import com.nicholas.rutherford.moments.ext.useFloatingActionButtonRobot
import com.nicholas.rutherford.moments.ext.useHomeScreenRobot
import com.nicholas.rutherford.moments.ext.useTopAppBarRobot
import com.nicholas.rutherford.moments.navigation.Navigator
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class NavigationComponentTest : KoinTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val navigator: Navigator by inject()

    @Test
    fun verify_home_screen_empty_state() {
        val application = ApplicationProvider.getApplicationContext<Application>()

        composeTestRule.setContent {
            val navController = rememberNavController()
            NavigationComponent(
                application = application,
                navHostController = navController,
                navigator = navigator
            )
        }

        composeTestRule.useTopAppBarRobot {
            verifyTopAppBarTitleIsDisplayed()
            verifyTopAppBarTitleText(value = "Moments")
            verifyTopAppBarBackIconButtonIsNotDisplayed()
        }

        composeTestRule.useHomeScreenRobot {
            verifyEmptyStateTitleIsDisplayed()
            verifyEmptyStateTitleText(value = "No moments yet")
            verifyEmptyStateDescriptionIsDisplayed()
            verifyEmptyStateDescriptionText(value = "Tap the + button to create your first moment.")
        }

        composeTestRule.useFloatingActionButtonRobot {
            verifyFloatingActionButtonIsDisplayed()
        }
    }

    @Test
    fun verify_create_moment_state() {
        val application = ApplicationProvider.getApplicationContext<Application>()

        composeTestRule.setContent {
            val navController = rememberNavController()
            NavigationComponent(
                application = application,
                navHostController = navController,
                navigator = navigator
            )
        }

        composeTestRule.useFloatingActionButtonRobot {
            clickOnFloatingActionButton()
            verifyFloatingActionButtonIsNotDisplayed()
        }

        composeTestRule.useTopAppBarRobot {
            verifyTopAppBarTitleIsDisplayed()
            verifyTopAppBarTitleText(value = "Moments")
            verifyTopAppBarBackIconButtonIsDisplayed()
        }

        composeTestRule.useCreateMomentScreenRobot {
            verifyCreateMomentTitleIsDisplayed()
            verifyCreateMomentTitleText(value = "Create Moment")
            verifyMomentTextFieldTitleIsDisplayed()
            verifyMomentTextFieldTitleText(value = "Moment Title")
            verifyMomentTextFieldIsDisplayed()
            verifyMomentTextFieldText(value = "")
            typeMoment(textToType = "New Moment")
//            verifyCategoryTagButtonIsDisplayed()
//            clickOnCategoryTagButton()
        }

        runBlocking {
            delay(20000)
        }

    }
}

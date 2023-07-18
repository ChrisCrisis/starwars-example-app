package dev.glimmr.starwarssample.ui.screen.home

import android.annotation.SuppressLint
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import dev.glimmr.starwarssample.MainActivity
import dev.glimmr.starwarssample.data.model.Starship
import org.junit.Rule
import org.junit.Test

@SuppressLint("UnrememberedMutableState")
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun loading_list_shows_indicator() {
        composeTestRule.activity.setContent {
            HomeScreenContent(
                homeScreenState = mutableStateOf(
                    HomeScreenState.Loading
                ),
                onShipClicked = {},
            )
        }
        composeTestRule.onNodeWithText("Loading", substring = true).assertIsDisplayed()
    }

    @Test
    fun empty_list_shows_placeholder_text() {
        composeTestRule.activity.setContent {
            HomeScreenContent(
                homeScreenState = mutableStateOf(
                    HomeScreenState.Ready(
                        emptyList()
                    )
                ),
                onShipClicked = {},
            )
        }
        composeTestRule.onNodeWithText("No starship found").assertIsDisplayed()
    }

    @Test
    fun list_shows_ships() {
        composeTestRule.activity.setContent {
            HomeScreenContent(
                homeScreenState = mutableStateOf(
                    HomeScreenState.Ready(
                        listOf(
                            Starship(
                                "TestId",
                                "Test Name",
                                "Test Model",
                                "Test Company",
                                "1337",
                                "1337",
                                "Test Class",
                            )
                        )
                    )
                ),
                onShipClicked = {},
            )
        }
        composeTestRule.onNodeWithText("Test Name").assertIsDisplayed()
    }
}
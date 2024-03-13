package com.andreylindo.cataaschallenge.screens

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.andreylindo.cataaschallenge.fake.fakeCat1
import com.andreylindo.cataaschallenge.ui.screens.details.DetailsScreen
import com.andreylindo.cataaschallenge.ui.screens.details.DetailsScreenParams
import com.andreylindo.cataaschallenge.ui.theme.CataasChallengeTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Copyright © 2024 CataasChallenge. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/13/24
 */
@RunWith(AndroidJUnit4::class)
class DetailsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_details_screen() {
        composeTestRule.setContent {
            CataasChallengeTheme {
                val navHostController = rememberNavController()
                DetailsScreen(
                    navHostController,
                    DetailsScreenParams(fakeCat1)
                )
            }
        }

        composeTestRule.onNodeWithText("Details").assertIsDisplayed()
        composeTestRule.onAllNodesWithContentDescription("Image of a cat")
            .assertCountEquals(1)
    }

}
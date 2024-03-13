package com.andreylindo.cataaschallenge.screens

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.andreylindo.cataaschallenge.common.ResourcesProvider
import com.andreylindo.cataaschallenge.data.NetworkResponse
import com.andreylindo.cataaschallenge.data.repository.CatsRepository
import com.andreylindo.cataaschallenge.fake.fakeCat1
import com.andreylindo.cataaschallenge.fake.fakeCat2
import com.andreylindo.cataaschallenge.fake.fakeCat3
import com.andreylindo.cataaschallenge.model.ui_model.toResponseModel
import com.andreylindo.cataaschallenge.ui.screens.home.HomeScreen
import com.andreylindo.cataaschallenge.ui.screens.home.HomeViewModel
import com.andreylindo.cataaschallenge.ui.theme.CataasChallengeTheme
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import org.junit.Before
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
private const val LIMIT = 20
private const val PAGE = 1

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @MockK
    lateinit var repository: CatsRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun test_home_screen_success() {
        coEvery { repository.getCats(LIMIT, PAGE) } returns NetworkResponse.Success(
            listOf(fakeCat1, fakeCat2, fakeCat3).map { it.toResponseModel() }
        )

        composeTestRule.setContent {
            CataasChallengeTheme {
                val navHostController = rememberNavController()

                HomeScreen(
                    navHostController,
                    HomeViewModel(
                        repository = repository,
                        resourcesProvider = ResourcesProvider(context = LocalContext.current)
                    ),
                )
            }
        }

        composeTestRule.onNodeWithText("Home").assertIsDisplayed()
        composeTestRule.onAllNodesWithContentDescription("Image of a cat")
            .assertCountEquals(3)
    }

    @Test
    fun test_home_screen_error() {
        coEvery { repository.getCats(LIMIT, PAGE) } returns NetworkResponse.Error(
            code = 404,
            message = "Not authorized"
        )

        composeTestRule.setContent {
            CataasChallengeTheme {
                val navHostController = rememberNavController()

                HomeScreen(
                    navHostController,
                    HomeViewModel(
                        repository = repository,
                        resourcesProvider = ResourcesProvider(context = LocalContext.current)
                    ),
                )
            }
        }

        composeTestRule.onNodeWithText("Home").assertIsDisplayed()
        composeTestRule.onNodeWithText("Not authorized").assertIsDisplayed()
    }

    @Test
    fun test_home_screen_exception() {
        coEvery { repository.getCats(LIMIT, PAGE) } returns NetworkResponse.Exception(
            Throwable("Unexpected Error")
        )

        composeTestRule.setContent {
            CataasChallengeTheme {
                val navHostController = rememberNavController()

                HomeScreen(
                    navHostController,
                    HomeViewModel(
                        repository = repository,
                        resourcesProvider = ResourcesProvider(context = LocalContext.current)
                    ),
                )
            }
        }

        composeTestRule.onNodeWithText("Home").assertIsDisplayed()
        composeTestRule.onNodeWithText("Unexpected Error").assertIsDisplayed()
    }
}
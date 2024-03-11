package com.andreylindo.cataaschallenge.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.andreylindo.cataaschallenge.common.EMPTY_STRING
import com.andreylindo.cataaschallenge.ui.screens.details.DetailsScreen
import com.andreylindo.cataaschallenge.ui.screens.home.HomeScreen
import com.andreylindo.cataaschallenge.ui.screens.home.HomeViewModel

/**
 * Copyright © 2024 CataasChallenge. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/11/24
 */

private const val IMAGE_URL_KEY = "imageUrl"

@Composable
fun MainNavGraph(navController: NavHostController, homeViewModel: HomeViewModel) {
    val durationMillis = 250

    NavHost(
        navController = navController,
        startDestination = Route.Home.routeName,
    )
    {
        composable(route = Route.Home.routeName) {
            HomeScreen(navController, homeViewModel)
        }
        composable(
            route = Route.Details().routeName + "?imageUrl={$IMAGE_URL_KEY}",
            arguments = listOf(
                navArgument(IMAGE_URL_KEY) {
                    type = NavType.StringType
                    defaultValue = EMPTY_STRING
                },
            ),
            enterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(durationMillis)
                )
            },
            popExitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(durationMillis)
                )
            },
        ) {
            val imageUrl = it.arguments?.getString(IMAGE_URL_KEY).orEmpty()
            DetailsScreen(navController, imageUrl = imageUrl)
        }
    }
}
package com.andreylindo.cataaschallenge.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.andreylindo.cataaschallenge.model.ui_model.CatUiModel
import com.andreylindo.cataaschallenge.ui.screens.details.DetailsScreen
import com.andreylindo.cataaschallenge.ui.screens.details.DetailsScreenParams
import com.andreylindo.cataaschallenge.ui.screens.home.HomeScreen
import com.andreylindo.cataaschallenge.ui.screens.home.HomeViewModel

/**
 * Copyright © 2024 CataasChallenge. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/11/24
 */

@Composable
fun MainNavGraph(navController: NavHostController, homeViewModel: HomeViewModel) {
    val durationMillis = 250

    NavHost(
        navController = navController,
        startDestination = Route.Home.routeName,
    )
    {
        composable(route = Route.Home.routeName) {
            HomeScreen(
                navHostController = navController,
                homeViewModel = homeViewModel
            )
        }
        composable(
            route = Route.Details.routeName,
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
            val params = NavigationHelper.getParcelable(it, DetailsScreenParams::class.java)

            DetailsScreen(
                navHostController = navController,
                params = DetailsScreenParams(
                    catUiModel = params?.catUiModel ?: CatUiModel()
                )
            )
        }
    }
}
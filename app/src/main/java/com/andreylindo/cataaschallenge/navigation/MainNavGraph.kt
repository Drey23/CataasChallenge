package com.andreylindo.cataaschallenge.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.andreylindo.cataaschallenge.common.EMPTY_STRING
import com.andreylindo.cataaschallenge.common.PARAMS_KEY
import com.andreylindo.cataaschallenge.ui.screens.details.DetailsScreen
import com.andreylindo.cataaschallenge.ui.screens.details.DetailsScreenParams
import com.andreylindo.cataaschallenge.ui.screens.home.HomeScreen
import com.andreylindo.cataaschallenge.ui.screens.home.HomeViewModel
import com.google.gson.Gson

/**
 * Copyright © 2024 CataasChallenge. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/11/24
 */

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun MainNavGraph(navController: NavHostController, homeViewModel: HomeViewModel) {
    val durationMillis = 250

    NavHost(
        navController = navController,
        startDestination = Route.Home().routeName,
    ) {
        composable(route = Route.Home().routeName) {
            HomeScreen(
                navHostController = navController, homeViewModel = homeViewModel
            )
        }
        composable(
            route = Route.Details(forNavMap = true).routeName,
            arguments = listOf(
                navArgument(PARAMS_KEY) {
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
            val params = Gson().fromJson(
                it.arguments?.getString(PARAMS_KEY),
                DetailsScreenParams::class.java
            )

            DetailsScreen(
                navHostController = navController,
                params = params
            )
        }
    }
}
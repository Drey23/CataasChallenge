package com.andreylindo.cataaschallenge.ui.screens.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController

/**
 * Copyright © 2024 CataasChallenge. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/11/24
 */

const val HOME_SCREEN_PARAMS_KEY = "homeScreenParams"
const val HOME_ROUTE_NAME = "home?$HOME_SCREEN_PARAMS_KEY=$HOME_SCREEN_PARAMS_KEY"


@Composable
fun HomeScreen(navHostController: NavHostController, homeViewModel: HomeViewModel) {

    LaunchedEffect(homeViewModel) {
        homeViewModel.getCats()
    }

    Text("")

//    Scaffold(
//        topBar = {
//            CustomTopAppBar(
//                title = LocalContext.current.getString(R.string.home),
//                navigationIcon = null,
//            )
//        }
//    ) { paddingValues ->
//        LazyVerticalGrid(
//            columns = GridCells.Fixed(3),
//            modifier = androidx.compose.ui.Modifier
//                .padding(paddingValues),
//        ) {
//            items(count = pokemons.size) { index ->
//                val pokemon = pokemons[index]
//                val imageUrl = pokemon.images?.large.orEmpty()
//                val name = pokemon.name
//
//                PokemonCard(
//                    imageUrl,
//                    name
//                ) {
//                    navController.navigate(RouteScreens.Details(imageUrl).route)
//                }
//            }
//        }
//    }
}

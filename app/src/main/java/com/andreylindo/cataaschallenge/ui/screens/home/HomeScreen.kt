package com.andreylindo.cataaschallenge.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.andreylindo.cataaschallenge.R
import com.andreylindo.cataaschallenge.navigation.Route
import com.andreylindo.cataaschallenge.ui.custom_composables.CustomTopAppBar
import com.andreylindo.cataaschallenge.ui.screens.details.DetailsScreenParams
import com.google.gson.Gson

/**
 * Copyright © 2024 CataasChallenge. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/11/24
 */

const val HOME_ROUTE_NAME = "home"

@Composable
fun HomeScreen(navHostController: NavHostController, homeViewModel: HomeViewModel) {

    val state = homeViewModel.state.observeAsState()

    LaunchedEffect(true) {
        if (state.value !is HomeState.Loaded) {
            homeViewModel.getCats()
        }
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = LocalContext.current.getString(R.string.home),
                navigationIcon = null,
            )
        }
    ) { paddingValues ->
        when (val stateValue = state.value) {
            is HomeState.Error -> {
                Text(
                    text = stateValue.message,
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                )
            }

            is HomeState.Loaded -> {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    verticalItemSpacing = 4.dp,
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    content = {
                        items(stateValue.cats.size) { index ->
                            val cat = stateValue.cats[index]

                            CatCard(url = cat.imageUrl) {
                                navHostController.navigate(
                                    Route.Details(
                                        params = Gson().toJson(DetailsScreenParams(cat))
                                    ).routeName
                                )
                            }
                        }
                    },
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxWidth()
                )
            }

            HomeState.Loading -> {
                LinearProgressIndicator(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxWidth(),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }


            else -> {
                EmptyView()
            }
        }
    }
}

@Composable
private fun EmptyView() {
    Box(modifier = Modifier)
}

package com.andreylindo.cataaschallenge.ui.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.andreylindo.cataaschallenge.R
import com.andreylindo.cataaschallenge.model.ui_model.CatUiModel
import com.andreylindo.cataaschallenge.ui.custom_composables.CustomTopAppBar
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

/**
 * Copyright © 2024 CataasChallenge. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/11/24
 */

const val DETAILS_ROUTE_NAME = "details_screen"

data class DetailsScreenParams(
    val catUiModel: CatUiModel
)

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailsScreen(
    navHostController: NavHostController,
    params: DetailsScreenParams
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = LocalContext.current.getString(R.string.details),
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                navIconClick = {
                    navHostController.popBackStack()
                }
            )
        }
    ) { paddingValues ->
        Column(
            Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            GlideImage(
                model = params.catUiModel.imageUrl,
                contentDescription = LocalContext.current.getString(R.string.image_of_a_cat),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 300.dp)
            )
            Spacer(modifier = Modifier.size(16.dp))

            val breeds = params.catUiModel.breeds

            if (breeds.any()) {
                Column(
                    Modifier.padding(PaddingValues(24.dp))
                ) {

                    val breed = params.catUiModel.breeds[0]

                    DetailsItem(
                        title = LocalContext.current.getString(R.string.breed_name),
                        description = breed.name
                    )
                    Spacer(modifier = Modifier.size(24.dp))
                    DetailsItem(
                        title = LocalContext.current.getString(R.string.description),
                        description = breed.description
                    )
                    Spacer(modifier = Modifier.size(24.dp))
                    DetailsItem(
                        title = LocalContext.current.getString(R.string.temperament),
                        description = breed.temperament
                    )
                    Spacer(modifier = Modifier.size(24.dp))
                    DetailsItem(
                        title = LocalContext.current.getString(R.string.origin),
                        description = breed.origin
                    )
                    Spacer(modifier = Modifier.size(24.dp))
                    DetailsItem(
                        title = LocalContext.current.getString(R.string.weight),
                        description = LocalContext.current.getString(
                            R.string.weight_values,
                            breed.weightImperial,
                            breed.weightMetric
                        )
                    )
                    Spacer(modifier = Modifier.size(24.dp))
                }
            }

        }
    }
}
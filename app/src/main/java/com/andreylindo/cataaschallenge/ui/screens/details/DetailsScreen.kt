package com.andreylindo.cataaschallenge.ui.screens.details

import android.os.Parcelable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.andreylindo.cataaschallenge.common.PARAMS_KEY
import com.andreylindo.cataaschallenge.model.ui_model.CatUiModel
import kotlinx.parcelize.Parcelize

/**
 * Copyright © 2024 CataasChallenge. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/11/24
 */

const val DETAILS_ROUTE_NAME = "details_screen?$PARAMS_KEY={$PARAMS_KEY}"

@Parcelize
data class DetailsScreenParams(
    val catUiModel: CatUiModel
) : Parcelable

@Composable
fun DetailsScreen(navHostController: NavHostController, params: DetailsScreenParams) {
    Text("Details")
}
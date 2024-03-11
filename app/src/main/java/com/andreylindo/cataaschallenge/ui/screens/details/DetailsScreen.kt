package com.andreylindo.cataaschallenge.ui.screens.details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

/**
 * Copyright © 2024 CataasChallenge. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/11/24
 */
@Composable
fun DetailsScreen(navHostController: NavHostController, imageUrl: String) {
    Text(imageUrl)
}
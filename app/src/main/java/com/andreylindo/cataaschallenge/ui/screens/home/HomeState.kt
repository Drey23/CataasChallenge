package com.andreylindo.cataaschallenge.ui.screens.home

import com.andreylindo.cataaschallenge.model.ui_model.CatUiModel

/**
 * Copyright © 2024 CataasChallenge. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/12/24
 */
sealed class HomeState {
    data class Loaded(val cats: List<CatUiModel>) : HomeState()
    data object Loading : HomeState()
    data class Error(val message: String) : HomeState()
}
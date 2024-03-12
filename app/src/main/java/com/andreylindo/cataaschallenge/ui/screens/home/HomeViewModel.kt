package com.andreylindo.cataaschallenge.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreylindo.cataaschallenge.common.TRANSLATION
import com.andreylindo.cataaschallenge.data.repository.CatsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Copyright © 2024 CataasChallenge. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/11/24
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: CatsRepository
): ViewModel() {

    fun getCats() {
        viewModelScope.launch {
            print(TRANSLATION)
            val response = repository.getCats(10, 1)
            print(response)
        }
    }
}
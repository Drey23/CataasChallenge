package com.andreylindo.cataaschallenge.ui.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreylindo.cataaschallenge.R
import com.andreylindo.cataaschallenge.common.ResourcesProvider
import com.andreylindo.cataaschallenge.data.NetworkResponse
import com.andreylindo.cataaschallenge.data.repository.CatsRepository
import com.andreylindo.cataaschallenge.model.response_model.toUiModel
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

private const val DEFAULT_LIMIT = 20

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: CatsRepository,
    private val resourcesProvider: ResourcesProvider
) : ViewModel() {

    private val _state = MutableLiveData<HomeState>()
    private var page = 1

    val state: LiveData<HomeState>
        get() = _state

    fun getCats() {
        viewModelScope.launch {
            _state.value = HomeState.Loading

            when (val response = repository.getCats(DEFAULT_LIMIT, page)) {
                is NetworkResponse.Error -> {
                    _state.value =
                        HomeState.Error(
                            message = response.message
                        )
                }

                is NetworkResponse.Exception -> {
                    _state.value =
                        HomeState.Error(
                            message = resourcesProvider.getString(R.string.unexpected_error)
                        )
                }

                is NetworkResponse.Success -> {
                    _state.value = HomeState.Loaded(response.data.map {
                        it.toUiModel()
                    })
                }
            }
        }
    }
}
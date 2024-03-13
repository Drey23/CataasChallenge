package com.andreylindo.cataaschallenge.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.andreylindo.cataaschallenge.R
import com.andreylindo.cataaschallenge.common.ResourcesProvider
import com.andreylindo.cataaschallenge.data.NetworkResponse
import com.andreylindo.cataaschallenge.data.repository.CatsRepository
import com.andreylindo.cataaschallenge.fake.fakeCat1
import com.andreylindo.cataaschallenge.fake.fakeCat2
import com.andreylindo.cataaschallenge.fake.fakeCat3
import com.andreylindo.cataaschallenge.model.ui_model.toResponseModel
import com.andreylindo.cataaschallenge.ui.screens.home.HomeState
import com.andreylindo.cataaschallenge.ui.screens.home.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Copyright © 2024 CataasChallenge. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/12/24
 */

private const val LIMIT = 20
private const val PAGE = 1

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var catsRepository: CatsRepository

    @Mock
    lateinit var resourcesProvider: ResourcesProvider

    @Mock
    private lateinit var observer: Observer<HomeState>

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)

        Mockito.`when`(resourcesProvider.getString(R.string.could_not_get_data))
            .thenReturn("Couldn't get data")

        Mockito.`when`(resourcesProvider.getString(R.string.unexpected_error))
            .thenReturn("Unexpected Error")
    }

    @Test
    fun test_getCats_success() = runTest {
        val vm = HomeViewModel(catsRepository, resourcesProvider)

        val cats = listOf(
            fakeCat1,
            fakeCat2,
            fakeCat3
        )

        Mockito.`when`(catsRepository.getCats(LIMIT, PAGE))
            .thenReturn(
                NetworkResponse.Success(
                    data = cats.map { it.toResponseModel() }
                )
            )

        vm.state.observeForever(observer)

        vm.getCats()

        Mockito.verify(observer).onChanged(HomeState.Loaded(cats))

        Mockito.verify(catsRepository, Mockito.atLeastOnce()).getCats(LIMIT, PAGE)
    }

    @Test
    fun test_getCats_error() = runTest {
        val vm = HomeViewModel(catsRepository, resourcesProvider)

        Mockito.`when`(catsRepository.getCats(LIMIT, PAGE))
            .thenReturn(
                NetworkResponse.Error(
                    message = "Not authorized",
                    code = 404
                )
            )

        vm.state.observeForever(observer)

        vm.getCats()

        Mockito.verify(observer).onChanged(HomeState.Error(message = "Not authorized"))

        Mockito.verify(catsRepository, Mockito.atLeastOnce()).getCats(LIMIT, PAGE)
    }

    @Test
    fun test_getCats_exception() = runTest {
        val vm = HomeViewModel(catsRepository, resourcesProvider)

        Mockito.`when`(catsRepository.getCats(LIMIT, PAGE))
            .thenReturn(
                NetworkResponse.Exception(
                    throwable = Throwable()
                )
            )

        vm.state.observeForever(observer)

        vm.getCats()

        Mockito.verify(observer).onChanged(HomeState.Error("Unexpected Error"))

        Mockito.verify(catsRepository, Mockito.atLeastOnce()).getCats(LIMIT, PAGE)
    }
}
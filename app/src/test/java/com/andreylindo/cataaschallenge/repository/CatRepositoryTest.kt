package com.andreylindo.cataaschallenge.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.andreylindo.cataaschallenge.api.CatsApi
import com.andreylindo.cataaschallenge.data.NetworkResponse
import com.andreylindo.cataaschallenge.data.repository_impl.CatsRepositoryImpl
import com.andreylindo.cataaschallenge.fake.fakeCat1
import com.andreylindo.cataaschallenge.fake.fakeCat2
import com.andreylindo.cataaschallenge.fake.fakeCat3
import com.andreylindo.cataaschallenge.model.ui_model.toResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.HttpException
import retrofit2.Response

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
class CatRepositoryTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var catsApi: CatsApi

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun test_getCats_success() = runTest {
        val repository = CatsRepositoryImpl(catsApi)

        val cats = listOf(
            fakeCat1,
            fakeCat2,
            fakeCat3
        )

        Mockito.`when`(catsApi.getCats(LIMIT, PAGE))
            .thenReturn(
                Response.success(cats.map { it.toResponseModel() })
            )

        val response = repository.getCats(LIMIT, PAGE)

        assert(response is NetworkResponse.Success)
        Mockito.verify(catsApi, Mockito.atLeastOnce()).getCats(LIMIT, PAGE)
    }

    @Test
    fun test_getCats_error() = runTest {
        val repository = CatsRepositoryImpl(catsApi)

        Mockito.`when`(catsApi.getCats(LIMIT, PAGE))
            .thenReturn(
                Response.error(404, byteArrayOf().toResponseBody())
            )

        val response = repository.getCats(LIMIT, PAGE)

        if (response is NetworkResponse.Error) {
            assert(response.code == 404)
        }

        assert(response is NetworkResponse.Error)
        Mockito.verify(catsApi, Mockito.atLeastOnce()).getCats(LIMIT, PAGE)
    }

    @Test
    fun test_getCats_http_exception() = runTest {
        val repository = CatsRepositoryImpl(catsApi)

        Mockito.`when`(catsApi.getCats(LIMIT, PAGE))
            .thenThrow(
                HttpException(Response.error<Any>(404, byteArrayOf().toResponseBody()))
            )

        val response = repository.getCats(LIMIT, PAGE)

        if (response is NetworkResponse.Error) {
            assert(response.code == 404)
        }

        assert(response is NetworkResponse.Error)
        Mockito.verify(catsApi, Mockito.atLeastOnce()).getCats(LIMIT, PAGE)
    }

    @Test
    fun test_getCats_exception() = runTest {
        val repository = CatsRepositoryImpl(catsApi)

        Mockito.`when`(catsApi.getCats(LIMIT, PAGE))
            .thenThrow(
                NullPointerException("Error")
            )

        val response = repository.getCats(LIMIT, PAGE)

        if (response is NetworkResponse.Exception) {
            assert(response.throwable.message == "Error")
        }

        assert(response is NetworkResponse.Exception)
        Mockito.verify(catsApi, Mockito.atLeastOnce()).getCats(LIMIT, PAGE)
    }
}
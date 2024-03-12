package com.andreylindo.cataaschallenge.api

import com.andreylindo.cataaschallenge.model.response_model.CatResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Copyright © 2024 CataasChallenge. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/11/24
 */

private const val HAS_BREEDS_DEFAULT_VALUE = 1

interface CatsApi {

    @GET("v1/images/search")
    suspend fun getCats(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("has_breeds") hasBreeds: Int = HAS_BREEDS_DEFAULT_VALUE
    ): Response<List<CatResponse>>
}
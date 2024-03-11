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

interface CatsApi {

    @GET("api/cats")
    suspend fun getCats(
        @Query("limit") limit: Int,
    ): Response<List<CatResponse>>
}
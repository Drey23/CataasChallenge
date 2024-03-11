package com.andreylindo.cataaschallenge.data.repository

import com.andreylindo.cataaschallenge.data.NetworkResponse
import com.andreylindo.cataaschallenge.model.response_model.CatResponse

/**
 * Copyright © 2024 CataasChallenge. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/11/24
 */
interface CatsRepository {

    suspend fun getCats(limit: Int): NetworkResponse<List<CatResponse>>
}
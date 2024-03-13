package com.andreylindo.cataaschallenge.data.repository_impl

import com.andreylindo.cataaschallenge.api.CatsApi
import com.andreylindo.cataaschallenge.data.repository.CatsRepository
import com.andreylindo.cataaschallenge.data.safeApiCall
import javax.inject.Inject

/**
 * Copyright © 2024 CataasChallenge. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/11/24
 */
class CatsRepositoryImpl @Inject constructor(private val api: CatsApi) : CatsRepository {
    override suspend fun getCats(limit: Int, page: Int) = safeApiCall {
        api.getCats(limit, page)
    }
}
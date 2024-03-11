package com.andreylindo.cataaschallenge.data.repository_impl

import com.andreylindo.cataaschallenge.api.CatsApi
import com.andreylindo.cataaschallenge.data.NetworkResponse
import com.andreylindo.cataaschallenge.data.repository.CatsRepository
import com.andreylindo.cataaschallenge.model.response_model.CatResponse
import retrofit2.HttpException
import javax.inject.Inject

/**
 * Copyright © 2024 CataasChallenge. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/11/24
 */
class CatsRepositoryImpl @Inject constructor(private val api: CatsApi) : CatsRepository {
    override suspend fun getCats(limit: Int): NetworkResponse<List<CatResponse>> {

        return try {
            val response = api.getCats(limit)
            val body = response.body()

            if (response.isSuccessful && body != null) {
                NetworkResponse.Success(body)
            } else {
                NetworkResponse.Error(code = response.code(), message = response.message())
            }
        } catch (e: HttpException) {
            NetworkResponse.Error(code = e.code(), message = e.message())
        } catch (e: Throwable) {
            NetworkResponse.Exception(e)
        }
    }
}
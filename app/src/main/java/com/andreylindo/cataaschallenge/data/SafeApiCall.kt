package com.andreylindo.cataaschallenge.data

import retrofit2.HttpException
import retrofit2.Response

/**
 * Copyright © 2024 CataasChallenge. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/11/24
 */
suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResponse<T> {
    return try {
        val call = apiCall.invoke()
        val body = call.body()

        if (call.isSuccessful && body != null) {
            NetworkResponse.Success(body)
        } else {
            NetworkResponse.Error(code = call.code(), message = call.message())
        }
    } catch (e: HttpException) {
        NetworkResponse.Error(code = e.code(), message = e.message())
    } catch (e: Exception) {
        NetworkResponse.Exception(e)
    }
}
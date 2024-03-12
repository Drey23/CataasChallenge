package com.andreylindo.cataaschallenge.data

/**
 * Copyright © 2024 CataasChallenge. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/11/24
 */
sealed class NetworkResponse<T> {
    data class Success<T>(val data: T) : NetworkResponse<T>()
    data class Error<T>(val message: String, val code: Int) : NetworkResponse<T>()
    data class Exception<T>(val throwable: Throwable) : NetworkResponse<T>()
}
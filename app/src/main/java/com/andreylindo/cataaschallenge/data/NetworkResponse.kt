package com.andreylindo.cataaschallenge.data

/**
 * Copyright © 2024 CataasChallenge. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/11/24
 */
sealed class NetworkResponse<T> {
    class Success<T>(val data: T) : NetworkResponse<T>()
    class Error<T>(val message: String, code: Int) : NetworkResponse<T>()
    class Exception<T>(val throwable: Throwable) : NetworkResponse<T>()
}
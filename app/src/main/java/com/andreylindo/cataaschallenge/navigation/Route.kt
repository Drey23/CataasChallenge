package com.andreylindo.cataaschallenge.navigation

/**
 * Copyright © 2024 CataasChallenge. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/11/24
 */
sealed class Route(val routeName: String) {

    data object Home : Route(routeName = "home_screen")

    data class Details(val imageUrl: String? = null) :
        Route(routeName = imageUrl?.let { "details_screen?imageUrl=$imageUrl" }
            ?: "details_screen")
}
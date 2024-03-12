package com.andreylindo.cataaschallenge.navigation

import com.andreylindo.cataaschallenge.ui.screens.details.DETAILS_ROUTE_NAME
import com.andreylindo.cataaschallenge.ui.screens.home.HOME_ROUTE_NAME

/**
 * Copyright © 2024 CataasChallenge. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/11/24
 */
sealed class Route(val routeName: String) {

    data object Home : Route(routeName = HOME_ROUTE_NAME)

    data object Details : Route(routeName = DETAILS_ROUTE_NAME)
}
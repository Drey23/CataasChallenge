package com.andreylindo.cataaschallenge.navigation

import com.andreylindo.cataaschallenge.common.PARAMS_KEY
import com.andreylindo.cataaschallenge.ui.screens.details.DETAILS_ROUTE_NAME
import com.andreylindo.cataaschallenge.ui.screens.details.DetailsScreenParams
import com.andreylindo.cataaschallenge.ui.screens.home.HOME_ROUTE_NAME

/**
 * Copyright © 2024 CataasChallenge. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/11/24
 */
sealed class Route(val routeName: String) {

    data class Home(val params: DetailsScreenParams? = null, val forNavMap: Boolean = false) :
        Route(
            routeName = HOME_ROUTE_NAME
        )

    data class Details(
        val params: String? = null,
        val forNavMap: Boolean = false,
    ) :
        Route(
            routeName = if (forNavMap) {
                "$DETAILS_ROUTE_NAME?$PARAMS_KEY={$PARAMS_KEY}"
            } else if (params != null) {
                "$DETAILS_ROUTE_NAME?$PARAMS_KEY=$params"
            } else {
                DETAILS_ROUTE_NAME
            }
        )
}

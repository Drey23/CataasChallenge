package com.andreylindo.cataaschallenge.navigation

import android.os.Build
import android.os.Parcelable
import androidx.navigation.NavBackStackEntry
import com.andreylindo.cataaschallenge.common.PARAMS_KEY

/**
 * Copyright © 2024 CataasChallenge. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/11/24
 */
object NavigationHelper {

    fun <T : Parcelable?> getParcelable(entry: NavBackStackEntry, clazz: Class<T>): T? {
        val model = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            entry.arguments?.getParcelable(PARAMS_KEY, clazz)
        } else {
            entry.arguments?.getParcelable(PARAMS_KEY)
        }

        return model
    }
}
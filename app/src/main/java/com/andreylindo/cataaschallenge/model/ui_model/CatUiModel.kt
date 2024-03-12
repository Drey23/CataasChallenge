package com.andreylindo.cataaschallenge.model.ui_model

import android.os.Parcelable
import com.andreylindo.cataaschallenge.common.EMPTY_STRING
import kotlinx.parcelize.Parcelize

/**
 * Copyright © 2024 CataasChallenge. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/11/24
 */
@Parcelize
data class CatUiModel(
    val id: String = EMPTY_STRING,
    val imageUrl: String = EMPTY_STRING,
    val breeds: List<BreedUiModel> = listOf()
) : Parcelable


@Parcelize
data class BreedUiModel(
    val name: String,
    val temperament: String,
    val origin: String,
    val description: String,
    val wikipediaUrl: String,
    val weightImperial: String,
    val weightMetric: String
) : Parcelable
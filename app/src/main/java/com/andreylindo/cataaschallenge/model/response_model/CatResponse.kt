package com.andreylindo.cataaschallenge.model.response_model

import com.andreylindo.cataaschallenge.common.BASE_URL
import com.andreylindo.cataaschallenge.common.EMPTY_STRING
import com.andreylindo.cataaschallenge.model.ui_model.BreedUiModel
import com.andreylindo.cataaschallenge.model.ui_model.CatUiModel
import com.google.gson.annotations.SerializedName

/**
 * Copyright © 2024 CataasChallenge. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/11/24
 */
data class CatResponse(
    val id: String?,

    @SerializedName("url")
    val imageUrl: String?,

    val breeds: List<BreedResponse>
)

data class BreedResponse(
    val name: String?,
    val temperament: String?,
    val origin: String?,
    val description: String?,

    @SerializedName("wikipedia_url")
    val wikipediaUrl: String?,

    val weight: BreedWeightResponse?
)

data class BreedWeightResponse(
    val imperial: String?,
    val metric: String?,
)

fun CatResponse.toUiModel() = CatUiModel(
    id = id ?: EMPTY_STRING,
    imageUrl = imageUrl ?: EMPTY_STRING,
    breeds = breeds.map {
        BreedUiModel(
            name = it.name ?: EMPTY_STRING,
            temperament = it.temperament ?: EMPTY_STRING,
            origin = it.origin ?: EMPTY_STRING,
            description = it.description ?: EMPTY_STRING,
            wikipediaUrl = it.wikipediaUrl ?: EMPTY_STRING,
            weightImperial = it.weight?.imperial ?: EMPTY_STRING,
            weightMetric = it.weight?.metric ?: EMPTY_STRING
        )
    }
)
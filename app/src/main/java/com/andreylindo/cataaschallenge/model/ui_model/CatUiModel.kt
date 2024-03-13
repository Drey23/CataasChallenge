package com.andreylindo.cataaschallenge.model.ui_model

import com.andreylindo.cataaschallenge.common.EMPTY_STRING
import com.andreylindo.cataaschallenge.model.response_model.BreedResponse
import com.andreylindo.cataaschallenge.model.response_model.BreedWeightResponse
import com.andreylindo.cataaschallenge.model.response_model.CatResponse

/**
 * Copyright © 2024 CataasChallenge. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/11/24
 */
data class CatUiModel(
    val id: String = EMPTY_STRING,
    val imageUrl: String = EMPTY_STRING,
    val breeds: List<BreedUiModel> = listOf()
)

data class BreedUiModel(
    val name: String,
    val temperament: String,
    val origin: String,
    val description: String,
    val wikipediaUrl: String,
    val weightImperial: String,
    val weightMetric: String
)

fun CatUiModel.toResponseModel() = CatResponse(
    id = id ?: EMPTY_STRING,
    imageUrl = imageUrl ?: EMPTY_STRING,
    breeds = breeds.map {
        BreedResponse(
            name = it.name,
            temperament = it.temperament,
            origin = it.origin,
            description = it.description,
            wikipediaUrl = it.wikipediaUrl,
            weight = BreedWeightResponse(
                metric = it.weightMetric,
                imperial = it.weightImperial
            )
        )
    }
)
package com.andreylindo.cataaschallenge.model.response_model

import com.andreylindo.cataaschallenge.common.BASE_URL
import com.andreylindo.cataaschallenge.common.EMPTY_STRING
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
    @SerializedName("_id")
    val id: String?,
    val tags: List<String>?
)

fun CatResponse.toUiModel() = CatUiModel(
    id = id ?: EMPTY_STRING,
    imageUrl = "$BASE_URL/cat/${id ?: EMPTY_STRING}",
    tags = tags ?: arrayListOf()
)
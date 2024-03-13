package com.andreylindo.cataaschallenge.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.andreylindo.cataaschallenge.R
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

/**
 * Copyright © 2024 CataasChallenge. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/12/24
 */
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CatCard(url: String, onTapped: () -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp)
    ) {
        GlideImage(
            model = url,
            contentScale = ContentScale.Fit,
            contentDescription = LocalContext.current.getString(R.string.image_of_a_cat),
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .clickable { onTapped.invoke() },
        )
    }
}
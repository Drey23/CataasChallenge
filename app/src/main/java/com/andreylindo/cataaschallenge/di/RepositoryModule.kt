package com.andreylindo.cataaschallenge.di

import com.andreylindo.cataaschallenge.api.CatsApi
import com.andreylindo.cataaschallenge.data.repository.CatsRepository
import com.andreylindo.cataaschallenge.data.repository_impl.CatsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Copyright © 2024 CataasChallenge. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/11/24
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providePokemonRepository(api: CatsApi): CatsRepository {
        return CatsRepositoryImpl(api)
    }
}
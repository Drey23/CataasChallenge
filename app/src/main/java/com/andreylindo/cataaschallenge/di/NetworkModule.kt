package com.andreylindo.cataaschallenge.di

import com.andreylindo.cataaschallenge.api.CatsApi
import com.andreylindo.cataaschallenge.common.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideCatsApi(retrofit: Retrofit): CatsApi {
        return retrofit.create(CatsApi::class.java)
    }
}
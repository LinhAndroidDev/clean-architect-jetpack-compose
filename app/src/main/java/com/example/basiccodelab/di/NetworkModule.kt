package com.example.basiccodelab.di

import com.example.basiccodelab.data.network.ApiServiceClient
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi
        .Builder()
        .run {
            add(KotlinJsonAdapterFactory())
            build()
        }

    @Provides
    @Singleton
    fun provideApiServiceClient(moshi: Moshi): ApiServiceClient =
        Retrofit.Builder()
            .baseUrl(ApiServiceClient.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ApiServiceClient::class.java)
}
package com.example.basiccodelab.di

import com.example.basiccodelab.data.network.repository.MovieRepositoryImpl
import com.example.basiccodelab.data.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideMovieRepository(repo: MovieRepositoryImpl): MovieRepository
}
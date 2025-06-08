package com.mehdizadeh.catfeed.di

import com.mehdizadeh.catfeed.data.repository.CatBreedRepositoryImpl
import com.mehdizadeh.catfeed.data.repository.ThemeRepositoryImpl
import com.mehdizadeh.catfeed.domain.repository.CatBreedRepository
import com.mehdizadeh.catfeed.domain.repository.ThemeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideThemeRepository(themeRepositoryImpl: ThemeRepositoryImpl): ThemeRepository
    @Binds
    abstract fun provideCatBreedRepository(catBreedRepositoryImpl: CatBreedRepositoryImpl): CatBreedRepository
}
package com.mehdizadeh.catfeed.di.networking

import com.mehdizadeh.catfeed.di.ApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {
    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): ApiServices =
        retrofit.create(ApiServices::class.java)


}
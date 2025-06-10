package com.mehdizadeh.catfeed.di.networking

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Network {
    private const val CACHE_SIZE = (10 * 1024 * 1024).toLong()
    private const val BASE_URL = "https://api.thecatapi.com/"
    private const val API_KEY = "live_Ug3jYoYsbhuPCQoZoxIzqLVrOnhVKEg88hh4LJXGEljjaWdknxBR57WgOTJZxNBa"

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideCache(@ApplicationContext context: Context): Cache {
        val cacheDir = File(context.cacheDir, "http_cache")
        return Cache(cacheDir, CACHE_SIZE)
    }
    @Provides
    @Singleton
    fun provideOfflineCacheInterceptor(
        @ApplicationContext context: Context
    ): OfflineCacheInterceptor {
        return OfflineCacheInterceptor(context)
    }

    @Provides
    @Singleton
    fun provideCacheInterceptor(): CacheInterceptor {
        return CacheInterceptor()
    }
    @Provides
    @Singleton
    fun provideApiKeyInterceptor(): ApiKeyInterceptor = ApiKeyInterceptor(API_KEY)

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        cache: Cache,
        apiKeyInterceptor: ApiKeyInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
        offlineCacheInterceptor: OfflineCacheInterceptor,
        cacheInterceptor: CacheInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(apiKeyInterceptor)
//            .addInterceptor(offlineCacheInterceptor)
//            .addNetworkInterceptor(cacheInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}
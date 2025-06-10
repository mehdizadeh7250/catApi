package com.mehdizadeh.catfeed.di.networking

import okhttp3.Interceptor
import okhttp3.Response

class CacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val maxAge = 60 * 60 * 24 * 7
        return response.newBuilder()
            .header("Cache-Control", "public, max-age=$maxAge")
            .build()
    }
}
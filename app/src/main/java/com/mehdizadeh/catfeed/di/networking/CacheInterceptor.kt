package com.mehdizadeh.catfeed.di.networking

import okhttp3.Interceptor
import okhttp3.Response

class CacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val maxAge = 60 * 60 * 24 * 7
        val request = chain.request()

        return if (request.header("Cache-Control") != null) {
            chain.proceed(request)
        } else {
            val modifiedRequest = request.newBuilder()
                .header("Cache-Control", "public, max-age=$maxAge")
                .build()
            chain.proceed(modifiedRequest)
        }
    }
}
package com.mehdizadeh.catfeed.di.networking

import okhttp3.Interceptor
import okhttp3.Response

class NoCacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val isNoCache = originalRequest.header("X-No-Cache") == "true"

        val requestBuilder = originalRequest.newBuilder()

        if (isNoCache) {
            requestBuilder
                .removeHeader("X-No-Cache")
                .header("Cache-Control", "no-store")
                .removeHeader("Pragma")
        }

        return chain.proceed(requestBuilder.build())
    }
}
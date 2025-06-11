package com.mehdizadeh.catfeed.di.networking

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class OfflineCacheInterceptor @Inject constructor(@ApplicationContext private val context: Context) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (!isNetworkAvailable()) {
            val maxStale = 60 * 60 * 24 * 7
            request = request.newBuilder()
                .header("Cache-Control", "public, max-stale=$maxStale")
                .build()
        }
        return chain.proceed(request)
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}
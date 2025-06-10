package com.mehdizadeh.catfeed.utils

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

fun Context.openWebPage(url: String) {
    val intent = Intent(Intent.ACTION_VIEW, url.toUri())
    startActivity(intent)
}
package com.github.jasonhezz.likesplash.util.network

import android.content.Context
import android.webkit.WebSettings
import okhttp3.Interceptor
import okhttp3.Response

class UserAgentInterceptor(val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
                .removeHeader("User-Agent")//移除旧的
                .addHeader("User-Agent", WebSettings.getDefaultUserAgent(context))
                .build()
        return chain.proceed(request)
    }
}
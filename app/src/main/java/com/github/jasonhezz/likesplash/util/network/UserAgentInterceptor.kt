package com.github.jasonhezz.likesplash.util.network

import android.webkit.WebSettings
import com.github.jasonhezz.likesplash.App
import okhttp3.Interceptor
import okhttp3.Response

class UserAgentInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .removeHeader("User-Agent")//移除旧的
            .addHeader("User-Agent", WebSettings.getDefaultUserAgent(App.applicationContext()))
            .build()
        return chain.proceed(request)
    }
}
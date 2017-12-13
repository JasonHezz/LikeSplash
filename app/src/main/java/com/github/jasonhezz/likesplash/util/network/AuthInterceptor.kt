package com.github.jasonhezz.likesplash.data.remote

import com.github.jasonhezz.likesplash.data.api.CLIENT_ID
import okhttp3.Interceptor
import okhttp3.Response

/**
 *
 * </p>
 */
const val AUTHORIZATION_HEADER = "Authorization"
const val AUTHORIZATION_PREFIX = "Client-ID "
const val BEARER = "Bearer"

class AuthInterceptor : Interceptor {

  var accessToken: String? = null

  override fun intercept(chain: Interceptor.Chain): Response {
    var request = chain.request().newBuilder()
        .addHeader(AUTHORIZATION_HEADER,
            "${AUTHORIZATION_PREFIX}${CLIENT_ID}")
        .build()
    if (accessToken != null) {
      request = chain.request()?.newBuilder()
          ?.addHeader(AUTHORIZATION_HEADER,
              "${BEARER}$accessToken")
          ?.build()
    }
    return chain.proceed(request)
  }
}
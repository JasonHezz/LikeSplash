package com.github.jasonhezz.likesplash.util.network

import android.content.Context
import okhttp3.*
import java.io.IOException

/**
 * Create fake response from json files from asse
 */
class FakeInterceptor(val context: Context) : Interceptor {

  override fun intercept(chain: Interceptor.Chain): Response {
    val url = chain.request().url().url()
    if (url.toString().contains(MOCK_API)) {
      val fileName = getFileName(chain)
      if (fileName != null) {
        try {
          val inputStream = context.assets.open(fileName)
          val size = inputStream.available()
          val buffer = ByteArray(size)
          inputStream.read(buffer)
          inputStream.close()
          val json = String(buffer, Charsets.UTF_8)
          return Response.Builder()
              .code(200)
              .message(MESSAGE)
              .request(chain.request())
              .protocol(Protocol.HTTP_1_0)
              .body(ResponseBody.create(MediaType.parse(CONTENT_TYPE),
                  json))
              .addHeader("content-type", CONTENT_TYPE)
              .build()
        } catch (e: IOException) {
          e.printStackTrace()
        }
      } else {
        throw RuntimeException("file not found")
      }
    }
    return chain.proceed(chain.request())
  }

  private fun getFileName(chain: Interceptor.Chain): String? {
    val fileName = chain.request().url().pathSegments()[chain.request().url().pathSegments().size - 1]
    return fileName + FILE_EXTENSION
  }

  companion object {
    const val MOCK_API = "http://mock.api"
    const val CONTENT_TYPE = "application/json"
    const val FILE_EXTENSION = ".json"
    const val MESSAGE = "OK!"
  }
}
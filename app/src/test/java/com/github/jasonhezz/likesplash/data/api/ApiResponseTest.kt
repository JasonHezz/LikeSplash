package com.github.jasonhezz.likesplash.data.api

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

/**
 * Created by JavaCoder on 2017/12/12.
 */
@RunWith(JUnit4::class)
class ApiResponseTest {

  @Test
  fun getNextPage() {
    val link = ("<https://api.unsplash.com/users/joshuaearle/followers?page+=+20&page=1>; rel=\"first\","
        + " <https://api.unsplash.com/users/joshuaearle/followers?page+=+20&page=19>; rel=\"prev\","
        + " <https://api.unsplash.com/users/joshuaearle/followers?page+=+20&page=601>; rel=\"last\","
        + " <https://api.unsplash.com/users/joshuaearle/followers?page+=+20&page=21>; rel=\"next\"")
    val headers = okhttp3.Headers.of("link", link)
    val response = ApiResponse(Response.success("foo", headers))
    Assert.assertEquals(response.nextPage, 21)
  }

  @Test
  fun getFirstPage() {
  }

  @Test
  fun getPrevPage() {
  }

  @Test
  fun getLastPage() {
  }
}
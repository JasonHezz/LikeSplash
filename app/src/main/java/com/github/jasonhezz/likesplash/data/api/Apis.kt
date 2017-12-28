package com.github.jasonhezz.likesplash.data.api

import android.support.annotation.IntRange
import android.support.annotation.StringDef
import com.github.jasonhezz.likesplash.data.*
import com.github.jasonhezz.likesplash.data.Collection
import io.reactivex.Single

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


/**
 * Created by JavaCoder on 2017/7/25.
 */
const val CLIENT_ID = "c94869b36aa272dd62dfaeefed769d4115fb3189a9d1ec88ed457207747be626"//"ca2305e1f165c879c362d1304230db396667e780f612b1693aa76b75089c655c"
const val CLIENT_SECRET = "8890bd9fb36ed81cacad12f6ffcb9d31f49ee7fd8db9cc229bdafeebb067d020"
const val REDIRECT_URI = "likesplash://likesplash-auth-callback"
const val GRANT_TYPE = "authorization_code"

val UNSPLASH_BASE_URL = "https://api.unsplash.com/"
val UNSPLASH_NEW_BASE_URL = "https://unsplash.com/napi/"
val UNSPLASH_URL = "https://unsplash.com/"

const val LATEST = "latest"
const val OLDEST = "oldest"
const val POPULAR = "popular"

const val LANDSCAPE = "landscape"
const val SQUARISH = "squarish"
const val PORTRAIT = "portrait"

const val DAYS = "days"

@StringDef(LATEST,
    OLDEST,
    POPULAR)
annotation class OrderBy

/**
 * Retrofit skips null parameters and ignores them while assembling the request
 * https://futurestud.io/tutorials/retrofit-optional-query-parameters
 */
interface AuthorizeService {

  @POST("oauth/token")
  fun getAccessToken(@Query("client_id") client_id: String,
      @Query("client_secret") client_secret: String,
      @Query("redirect_uri") redirect_uri: String,
      @Query("code") code: String,
      @Query("grant_type") grant_type: String): Single<AccessToken>
}


interface PhotoService {

  @GET("photos/")
  fun getListPhotos(@Query("page") page: Int = 1, @Query("per_page") perPage: Int = 10,
      @Query("order_by") orderBy: String = LATEST): Single<List<Photo>>

  @GET("photos/curated")
  fun getListCuratedPhotos(@Query("page") page: Int = 1, @Query("per_page") perPage: Int = 10,
      @OrderBy @Query("order_by") orderBy: String = LATEST): Single<List<Photo>>

  @GET("photos/{id}")
  fun getAPhoto(@Path("id") id: String, @Query("w") w: Int? = null, @Query("h") h: Int? = null):
      Single<Photo>

  @GET("photos/random")
  fun getListRandomPhoto(@Query("collections") collections: String? = null,
      @Query("featured") featured: String? = null,
      @Query("username") username: String? = null,
      @Query("query") query: String? = null,
      @Query("orientation") orientation: String? = null,
      @Query("w") w: Int? = null,
      @Query("h") h: Int? = null,
      @IntRange(from = 1, to = 30) @Query(
          "count") count: Int = 1): Single<List<Photo>>

  @GET("photos/{id}/statistics")
  fun getAPhotoStatistics(@Path("id") id: Int, @Query("resolution") resolution: String = DAYS,
      @Query("quantity") quantity: Int = 30)

  @GET("photos/{id}/download")
  fun getAPhotoDownloadLink(@Path("id") id: String): Single<DownLoadLink>

  //Update a photo on behalf of the logged-in user. This requires the write_photos scope.
  @PUT("photos/{id}")
  fun updateAPhoto(@Path("id") id: String)

  @POST("photos/{id}/like")
  fun likeAPhoto(@Path("id") id: String)

  //Note: This action is idempotent; sending the DELETE request to a single photo multiple times has no additional effect.
  @DELETE("photos/{id}/like")
  fun unlikeAPhoto(@Path("id") id: String)
}

interface MeService {
  //Note: To access a user’s private data, the user is required to authorize the read_user scope.
  // Without it, this request will return a 403 Forbidden response.
  @GET("me")
  fun getMeProfile(): Single<Me>

  @PUT("me")
  fun updateMeProfile(@Query("username") username: String? = null,
      @Query("first_name") first_name: String? = null,
      @Query("last_name") last_name: String? = null,
      @Query("email") email: String? = null,
      @Query("url") url: String? = null,
      @Query("location") location: String? = null,
      @Query("bio") bio: String? = null): Single<Me>
}


interface UserService {

  @GET("users/{username}")
  fun getUserProfile(@Path("username") username: String,
      @Query("w") w: Int?,
      @Query("h") h: Int?): Single<User>

  @GET("users/{username}/following")
  fun getUserFollowing(@Path("username") username: String,
      @Query("page") page: Int,
      @Query("per_page") per_page: Int): Single<List<User>>

  @GET("users/{username}/followers")
  fun getUserFollowers(@Path("username") username: String,
      @Query("page") page: Int,
      @Query("per_page") per_page: Int): Single<List<User>>

  @GET("users/{username}/likes")
  fun getUserLikes(@Path("username") username: String,
      @Query("page") page: Int,
      @Query("per_page") per_page: Int,
      @Query("order_by") orderBy: String = LATEST): Single<List<Photo>>

  @GET("users/{username}/photos")
  fun getUserPhotos(@Path("username") username: String,
      @Query("page") page: Int,
      @Query("per_page") per_page: Int,
      @Query("order_by") orderBy: String = LATEST): Single<List<Photo>>

  @GET("users/{username}/collections")
  fun getUserCollection(@Path("username") username: String,
      @Query("page") page: Int,
      @Query("per_page") per_page: Int,
      @Query("order_by") orderBy: String = LATEST): Single<List<Collection>>

  @GET("users/{username}/statistics")
  fun getUserStatistics(@Path("username") username: String,
      @Query("resolution") resolution: String = DAYS,
      @Query("quantity") quantity: Int = 30)

  @POST("users/{username}/follow")
  fun followUser(@Path("username") username: String)

  @DELETE("users/{username}/follow")
  fun unfollowUser(@Path("username") username: String)
}

interface CollectionService {
  @GET("collections/")
  fun getListCollections(@Query("page") page: Int = 1,
      @Query("per_page") perPage: Int = 10): Call<List<Collection>>

  @GET("collections/curated")
  fun getListCuratedCollections(@Query("page") page: Int = 1,
      @Query("per_page") perPage: Int = 10): Call<List<Collection>>

  @GET("collections/curated")
  fun getListFeaturedCollections(@Query("page") page: Int = 1,
      @Query("per_page") perPage: Int = 10): Call<List<Collection>>

  @GET("collections/curated/{id}")
  fun getACollection(@Path("id") id: String, @Query("page") page: Int = 1,
      @Query("per_page") perPage: Int = 10): Single<Collection>

  @GET("collections/curated/{id}")
  fun getACuratedCollection(@Path("id") id: String, @Query("page") page: Int = 1,
      @Query("per_page") perPage: Int = 10): Single<Collection>

  @GET("collections/{id}/photos")
  fun getCollectionPhotos(@Path("id") id: String, @Query("page") page: Int = 1,
      @Query("per_page") perPage: Int = 10): Single<List<Photo>>

  @GET("collections/curated/{id}/photos")
  fun getCuratedCollectionPhotos(@Path("id") id: String, @Query("page") page: Int = 1,
      @Query("per_page") perPage: Int = 10): Single<List<Photo>>

  @GET("collections/{id}/related")
  fun getRelatedCollections(@Path("id") id: String)

  @POST("collections")
  fun createANewCollection(@Query("title") title: String,
      @Query("description") description: String? = null,
      @Query("private") private: Boolean): Single<Collection>

  @POST("collections/{id}")
  fun updateANewCollection(@Path("id") id: String): Single<Collection>

  @DELETE("collections/{id}")
  fun deleteCollection(@Path("id") id: String): Single<ResponseBody>

  @POST("collections/collection_id/add")
  fun addPhotoToCollection(@Path("collection_id") collection_id: Int,
      @Query("photo_id") photo_id: String): Single<ResponseBody>

  //Remove a photo from one of the logged-in user’s collections. Requires the write_collections scope
  @POST("collections/collection_id/remove")
  fun removePhotoToCollection(@Path("collection_id") collection_id: Int,
      @Query("photo_id") photo_id: String): Single<ResponseBody>
}

interface SearchService {

  @GET("search/photos")
  fun searchPhotos(@Query("query") query: String,
      @Query("page") page: Int,
      @Query("per_page") per_page: Int): Single<SearchPhotoResult>

  @GET("search/collections")
  fun searchCollections(@Query("query") query: String,
      @Query("page") page: Int,
      @Query("per_page") per_page: Int): Single<List<Collection>>

  @GET("search/users")
  fun searchUsers(@Query("query") query: String,
      @Query("page") page: Int,
      @Query("per_page") per_page: Int): Single<List<User>>
}

interface StatsService {

  @GET("stats/total")
  fun getTotal(): Single<TotalStats>

  @GET("/stats/month")
  fun getMonth(): Single<MonthStats>
}

interface TrendingService {

  @GET("feeds/home")
  fun getTrendingFeed(@Query("after") after: String? = null): Single<TrendingFeed>

  @GET("feeds/home")
  fun getTrendingFeed(@Query("after") after: String? = null, @Query(
      "per_page") per_page: Int): Call<TrendingFeed>

  @GET("feeds/following")
  fun getFollowingFeed(@Query("after") after: String? = null)
}
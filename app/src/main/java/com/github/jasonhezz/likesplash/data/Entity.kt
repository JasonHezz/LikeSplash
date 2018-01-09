package com.github.jasonhezz.likesplash.data

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable


/**
 * Created by JasonHezz on 2017/7/11.
 */
//@Parcelize not work under lolipop will throw install_failed_uid_changed
data class Photo(var id: String,
    var created_at: String,
    var updated_at: String,
    var width: Int,
    var height: Int,
    var color: String,
    val slug: Any? = null,//not use now
    val downloads: Int? = 0,
    var likes: Int? = 0,
    val views: Int? = 0,
    val liked_by_user: Boolean?,
    val description: String?,
    val exif: Exif? = null,
    val location: Location?,
    val current_user_collections: List<Collection>? = null,
    val urls: Urls?,
    val categories: List<Categories>?,
    val links: PhotoLinks?,
    val story: Story?,
    var tags: List<Tag>?,
    val relatedCollections: RelatedCollections? = null,
    var user: User?) : Serializable

data class CoverPhoto(var id: String?,
    var width: Int?,
    var height: Int?,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    var color: String?,
    var likes: Int?,
    var liked_by_user: Boolean?,
    var description: String?,
    var user: User?,
    var urls: Urls?,
    var links: PhotoLinks?,
    var categories: List<Categories>?)

data class Location(
    val country: String? = null,
    val city: String? = null,
    val name: String? = null,
    val position: Position? = null,
    val title: String? = null
)

data class Position(
    val latitude: Double? = null,
    val longitude: Double? = null
)

data class RelatedCollections(
    val total: Int? = null,
    val type: String? = null,
    val results: List<Collection>? = null
)

data class Story(
    val description: Any? = null,
    val title: Any? = null
)

data class Exif(
    val exposureTime: String? = null,
    val aperture: String? = null,
    val focalLength: String? = null,
    val iso: Int? = null,
    val model: String? = null,
    val make: String? = null
)

@SuppressLint("ParcelCreator")
@Parcelize
data class User(var id: String?,
    var updated_at: String?,
    var username: String?,
    var name: String?,
    var first_name: String?,
    var last_name: String?,
    var twitter_username: String?,
    var portfolio_url: String?,
    var bio: String?,
    val followedByUser: Boolean? = false,
    var location: String?,
    var total_likes: Int? = 0,
    var total_photos: Int? = 0,
    var total_collections: Int?,
    var profile_image: ProfileImage?,
    var links: UserLinks?) : Parcelable

data class Tag(val title: String? = null,
    val url: String? = null, val description: String? = null)

data class Me(var id: String? = null,
    var username: String? = null,
    var first_name: String? = null,
    var last_name: String? = null,
    var portfolio_url: String? = null,
    var bio: String? = null,
    var location: String?,
    var total_likes: Int = 0,
    var total_photos: Int = 0,
    var total_collections: Int = 0,
    var followed_by_user: Boolean = false,
    var downloads: Int = 0,
    var uploads_remaining: Int = 0,
    var instagram_username: String? = null,
    var email: String? = null,
    var links: UserLinks? = null)

@SuppressLint("ParcelCreator")
@Parcelize
data class ProfileImage(var small: String?,
    var medium: String?,
    var large: String) : Parcelable


data class Urls(var raw: String?,
    var full: String?,
    var regular: String?,
    var small: String?,
    var thumb: String?,
    var custom: String?)

data class PhotoLinks(var self: String?,
    var html: String?,
    var download: String?,
    var download_location: String?)

data class CollectionLinks(var self: String?,
    var html: String?,
    var photos: String?,
    var related: String?)

data class Categories(var id: Int?,
    var title: String?,
    var photo_count: Int?,
    var links: PhotoLinks)

data class Collection(var id: Int?,
    val featured: Boolean? = null,
    var title: String?,
    var description: String?,
    var published_at: String?,
    var updated_at: String?,
    var curated: Boolean?,
    var total_photos: Int?,
    var private: Boolean?,
    var share_key: String?,
    var cover_photo: Photo?,
    var preview_photos: List<Photo>?,
    var user: User?,
    var links: CollectionLinks)

data class AccessToken(var access_token: String?,
    var token_type: String?,
    var scope: String?,
    var created_at: String?)

data class DownLoadLink(var url: String)

@SuppressLint("ParcelCreator")
@Parcelize
data class UserLinks(var self: String?,
    var html: String?,
    var photos: String?,
    var likes: String?,
    var portfolio: String?,
    var following: String?,
    var followers: String?) : Parcelable


data class TotalStats(var photos: Int?,
    var download: Int?,
    var views: Int?,
    var likes: Int?,
    var photographers: Int?,
    var pixels: Int?,
    var downloads_per_second: Int,
    var views_per_second: Int,
    var developers: Int?,
    var applications: Int?,
    var requests: Int)

data class MonthStats(var photos: Int?,
    var download: Int?,
    var views: Int?,
    var likes: Int?,
    var new_photos: Int?,
    var new_photographers: Int?,
    var new_pixels: Int?,
    var new_developers: Int?,
    var new_applications: Int?,
    var new_requests: Int?)

data class TrendingFeed(var next_page: String?, var photos: List<Photo>?)

data class SearchPhotoResult(var total: Int?, var total_pages: Int?, var results: List<Photo>?)

data class ExplorePhoto(val name: String? = null, val descriptionFragment: String? = null,
    val related: List<Tag>? = null)


data class ExploreCollection(val name: String? = null, val descriptionFragment: String? = null,
    val collections: List<Collection>? = null)
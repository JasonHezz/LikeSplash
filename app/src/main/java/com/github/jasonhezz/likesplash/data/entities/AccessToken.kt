package com.github.jasonhezz.likesplash.data.entities

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class AccessToken(
        @Json(name = "access_token") val accessToken: String?,
        @Json(name = "token_type") val tokenType: String?,
        @Json(name = "scope") val scope: String?,
        @Json(name = "created_at") val createdAt: String?
) : Parcelable
package com.github.jasonhezz.likesplash.data.entities

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PreviewPhoto(
    @Json(name = "id") val id: Int?,
    @Json(name = "urls") val urls: Urls?
):Parcelable
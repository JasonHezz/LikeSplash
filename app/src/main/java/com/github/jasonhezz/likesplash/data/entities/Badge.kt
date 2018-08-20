package com.github.jasonhezz.likesplash.data.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Badge(
    @SerializedName("title") val title: String?,
    @SerializedName("primary") val primary: Boolean?,
    @SerializedName("slug") val slug: String?,
    @SerializedName("link") val link: String?
) : Parcelable
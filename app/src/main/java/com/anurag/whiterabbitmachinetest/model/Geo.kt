package com.anurag.whiterabbitmachinetest.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Geo(
    @SerializedName("lat")
    val lat: String?,
    @SerializedName("lng")
    val lng: String?
): Parcelable
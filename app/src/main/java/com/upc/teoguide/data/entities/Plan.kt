package com.upc.teoguide.data.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Plan (
    @SerializedName("id")
    val id: Int,

    @SerializedName("titulo")
    val titulo: String,

    @SerializedName("descripcion")
    val descripcion: String,

    @SerializedName("fechaPlan")
    val fechaPlan: String
):Parcelable
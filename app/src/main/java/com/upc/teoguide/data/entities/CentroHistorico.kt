package com.upc.teoguide.data.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CentroHistorico (

    @SerializedName("id")
    val id : Int,

    @SerializedName("nombre")
    val nombre: String,

    @SerializedName("descripcion")
    val descripcion: String,

    @SerializedName("imgUrl")
    val imgUrl: String

) : Parcelable
package com.upc.teoguide.data.remote.request

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.time.LocalDate
import java.util.*

@Parcelize
data class PlanReq(
    @SerializedName("usuarioId")
    val usuarioId: Int,

    @SerializedName("titulo")
    val titulo: String,

    @SerializedName("descripcion")
    val descripcion: String,

    @SerializedName("fechaPlan")
    val fechaPlan: String
): Parcelable
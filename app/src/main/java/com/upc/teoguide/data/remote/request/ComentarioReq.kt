package com.upc.teoguide.data.remote.request

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ComentarioReq (
    @SerializedName("usuarioId")
    val usuarioId: Int,

    @SerializedName("centroHistoricoId")
    val centroHistoricoId: Int,

    @SerializedName("texto")
    val texto: String,

    @SerializedName("actividades")
    val actividades: List<String> = ArrayList()
): Parcelable
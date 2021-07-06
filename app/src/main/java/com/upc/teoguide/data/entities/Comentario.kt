package com.upc.teoguide.data.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comentario (
    @SerializedName("id")
    val id: Int,

    @SerializedName("usuarioId")
    val usuarioId: Int,

    @SerializedName("centroHistoricoId")
    val centroHistoricoId: Int,

    @SerializedName("texto")
    val texto: String,

    @SerializedName("nombreUsuario")
    val nombreUsuario: String,

    @SerializedName("imagenUrlUsuario")
    val imagenUrlUsuario: String
): Parcelable
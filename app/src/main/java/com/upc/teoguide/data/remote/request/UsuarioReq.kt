package com.upc.teoguide.data.remote.request

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UsuarioReq (
    @SerializedName("nombres")
    val nombres: String,

    @SerializedName("apellidos")
    val apellidos: String,

    @SerializedName("correo")
    val correo: String,

    @SerializedName("contrasenya")
    val contrasenya: String,

    @SerializedName("imagenUrl")
    val imagenUrl: String,

    @SerializedName("tipo")
    val tipo: String,
): Parcelable
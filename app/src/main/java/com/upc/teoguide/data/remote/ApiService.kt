package com.upc.teoguide.data.remote

import com.upc.teoguide.data.entities.CentroHistorico
import com.upc.teoguide.data.remote.response.CentroHistoricoRes
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("centrohistoricos")
    fun getCentrosHistoricos(): Call<List<CentroHistorico>>
}
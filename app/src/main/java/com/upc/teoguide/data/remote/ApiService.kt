package com.upc.teoguide.data.remote

import com.upc.teoguide.data.entities.CentroHistorico
import com.upc.teoguide.data.entities.Comentario
import com.upc.teoguide.data.entities.Plan
import com.upc.teoguide.data.remote.request.ComentarioReq
import com.upc.teoguide.data.remote.request.PlanReq
import com.upc.teoguide.data.remote.request.UsuarioReq
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("centrohistoricos")
    fun getCentrosHistoricos(): Call<List<CentroHistorico>>

    @GET("comentarios/{param}")
    fun getComentarios(@Path("param") param: Int): Call<List<Comentario>>

    @GET("planes/{param}")
    fun getPlanesByUserId(@Path("param") param: Int): Call<List<Plan>>

    @POST("usuarios")
    fun postUsuario(@Body body: UsuarioReq): Call<Int>

    @POST("comentarios")
    fun postComentario(@Body body: ComentarioReq): Call<Int>

    @POST("planes")
    fun postPlan(@Body body: PlanReq): Call<Int>

}
package com.upc.teoguide.ui.principal.fragments.home

import android.util.Log
import androidx.annotation.Nullable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.upc.teoguide.data.entities.Comentario
import com.upc.teoguide.data.remote.ApiClient
import com.upc.teoguide.data.remote.request.ComentarioReq
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ComentarioViewModel: ViewModel() {

    private val comentarios: MutableLiveData<List<Comentario>> by lazy {
        MutableLiveData<List<Comentario>>()
    }

    fun start() {

    }

    fun getComentarios(centroId: Int): LiveData<List<Comentario>> {
        val getResponse = ApiClient.build()?.getComentarios(centroId)
        getResponse?.enqueue(object: Callback<List<Comentario>> {
            override fun onResponse(
                call: Call<List<Comentario>>,
                response: Response<List<Comentario>>
            ) {
                if(response.isSuccessful) {
                    comentarios.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<Comentario>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

        return comentarios
    }

    fun postComentario(comentario: ComentarioReq): LiveData<List<Comentario>> {
        var reload: LiveData<List<Comentario>> = MutableLiveData<List<Comentario>>()
        val postResponse = ApiClient.build()?.postComentario(comentario)
        postResponse?.enqueue(object: Callback<Int> {
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                reload = getComentarios(comentario.centroHistoricoId)
            }

            override fun onFailure(call: Call<Int>, t: Throwable) {
            }

        })
        return reload
    }
}
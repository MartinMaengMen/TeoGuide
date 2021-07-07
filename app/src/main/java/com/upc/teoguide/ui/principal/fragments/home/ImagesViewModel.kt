package com.upc.teoguide.ui.principal.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.upc.teoguide.data.entities.Multimedia
import com.upc.teoguide.data.remote.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImagesViewModel: ViewModel() {

    private val multimedias: MutableLiveData<List<Multimedia>> by lazy {
        MutableLiveData<List<Multimedia>>()
    }

    fun start() {

    }

    fun getMultimedias(centroId: Int): LiveData<List<Multimedia>> {
        val getResponse = ApiClient.build()?.getMultimediasByCentroId(centroId)
        getResponse?.enqueue(object: Callback<List<Multimedia>>{
            override fun onResponse(
                call: Call<List<Multimedia>>,
                response: Response<List<Multimedia>>
            ) {
                if(response.isSuccessful) {
                    multimedias.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<Multimedia>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

        return multimedias
    }
}
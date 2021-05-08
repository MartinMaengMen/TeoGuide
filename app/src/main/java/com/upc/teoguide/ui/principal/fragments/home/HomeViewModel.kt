package com.upc.teoguide.ui.principal.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.upc.teoguide.data.entities.CentroHistorico
import com.upc.teoguide.data.remote.response.CentroHistoricoRes
import com.upc.teoguide.data.remote.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val centroHistoricos: MutableLiveData<List<CentroHistorico>> by lazy {
        MutableLiveData<List<CentroHistorico>>()
    }

    fun start(){

    }

    fun getCentrosHistoricos() : LiveData<List<CentroHistorico>> {
        val getResponse = ApiClient.build()?.getCentrosHistoricos()
        getResponse?.enqueue(object : Callback<List<CentroHistorico>>{
            override fun onResponse(call: Call<List<CentroHistorico>>, response: Response<List<CentroHistorico>>){
                if(response.isSuccessful){
                    centroHistoricos.postValue( response.body())
                }
            }

            override fun onFailure(call: Call<List<CentroHistorico>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        return centroHistoricos
    }

}
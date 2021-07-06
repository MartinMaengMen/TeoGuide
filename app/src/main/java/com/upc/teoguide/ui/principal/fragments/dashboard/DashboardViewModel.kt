package com.upc.teoguide.ui.principal.fragments.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.upc.teoguide.data.entities.Plan
import com.upc.teoguide.data.remote.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardViewModel : ViewModel() {

    private val planes: MutableLiveData<List<Plan>> by lazy {
        MutableLiveData<List<Plan>>()
    }

    fun start() {

    }

    fun getPlanes(usuarioId: Int): LiveData<List<Plan>> {
        val getResponse = ApiClient.build()?.getPlanesByUserId(usuarioId)
        getResponse?.enqueue(object: Callback<List<Plan>> {
            override fun onResponse(call: Call<List<Plan>>, response: Response<List<Plan>>) {
                if(response.isSuccessful) {
                    planes.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<Plan>>, t: Throwable) {
                Log.d("error: ", t.toString())
            }

        })

        return planes
    }
}
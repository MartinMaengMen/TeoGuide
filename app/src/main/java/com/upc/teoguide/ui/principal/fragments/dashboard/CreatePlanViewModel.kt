package com.upc.teoguide.ui.principal.fragments.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.upc.teoguide.data.remote.ApiClient
import com.upc.teoguide.data.remote.request.PlanReq
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreatePlanViewModel: ViewModel() {

    fun postPlan(plan: PlanReq): LiveData<Boolean> {
        var _create = MutableLiveData<Boolean>(false)
        var created: LiveData<Boolean> = _create

        val postResponse = ApiClient.build()?.postPlan(plan)
        postResponse?.enqueue(object: Callback<Int> {
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                if(response.isSuccessful){
                    _create.postValue(true)
                }
            }

            override fun onFailure(call: Call<Int>, t: Throwable) {
                Log.d("plan err: ", t.toString())
            }
        })

        return created
    }
}
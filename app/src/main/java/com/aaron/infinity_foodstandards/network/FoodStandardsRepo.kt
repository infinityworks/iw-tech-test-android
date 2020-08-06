package com.aaron.infinity_foodstandards.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.aaron.infinity_foodstandards.model.LocalAuthoritiesResponse
import retrofit2.Call
import retrofit2.Response

class FoodStandardsRepo(private var api: FoodStandardsApi) {

    ///Get list of authorities from food standards API
    fun getLocalAuthorities(): MutableLiveData<LocalAuthoritiesResponse> {

        val apiData: MutableLiveData<LocalAuthoritiesResponse> = MutableLiveData()
        api.getAuthorities().enqueue(object : retrofit2.Callback<LocalAuthoritiesResponse> {
            override fun onResponse(
                call: Call<LocalAuthoritiesResponse>,
                response: Response<LocalAuthoritiesResponse>
            ) {
                if (response.isSuccessful) {
                    apiData.value = response.body()
                } else {
                    Log.e(TAG, response.message())
                    apiData.value = null
                }
            }

            override fun onFailure(call: Call<LocalAuthoritiesResponse>, t: Throwable) {
                t.localizedMessage?.let { Log.e(TAG, it) }
                apiData.value = null
            }
        })
        return apiData
    }

    companion object {
        private const val TAG = "REPO"
    }
}
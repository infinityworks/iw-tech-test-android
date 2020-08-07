package com.aaron.infinity_foodstandards.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.aaron.infinity_foodstandards.model.Establishment
import com.aaron.infinity_foodstandards.model.EstablishmentResponse
import com.aaron.infinity_foodstandards.model.LocalAuthoritiesResponse
import retrofit2.Call
import retrofit2.Response
import kotlin.random.Random

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

    ///Get list of establishments from API
    fun getEstablishments(id: Int): MutableLiveData<EstablishmentResponse> {
        val apiData: MutableLiveData<EstablishmentResponse> = MutableLiveData()

        val establishmentResponse = EstablishmentResponse()
        val establishment1 = Establishment("fhrs_5_en-gb")
        val establishment2 = Establishment("fhrs_4_en-gb")
        val establishment3 = Establishment("fhrs_3_en-gb")
        val establishment4 = Establishment("fhrs_2_en-gb")
        val establishment5 = Establishment("fhrs_1_en-gb")
        val establishment6 = Establishment("fhrs_exempt_en-gb")

        if(Random.nextBoolean()) {
            establishmentResponse.establishments = listOf(
                establishment2,
                establishment3,
                establishment4,
                establishment5
            )
        } else {
            establishmentResponse.establishments = listOf(
                establishment1,
                establishment6)
        }
        apiData.value = establishmentResponse

        return apiData
    }

    companion object {
        private const val TAG = "REPO"
    }
}
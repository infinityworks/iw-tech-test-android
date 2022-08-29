package com.infinity.foodstandards.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.infinity.foodstandards.model.Establishment
import com.infinity.foodstandards.model.EstablishmentResponse
import com.infinity.foodstandards.model.LocalAuthoritiesResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import kotlin.random.Random

class FoodStandardsRepo(
    private var api: FoodStandardsApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    // Get list of authorities from food standards API
    suspend fun getLocalAuthorities(): Result<LocalAuthoritiesResponse> = withContext(dispatcher) {
        try {
            Result.success(api.getAuthorities())
        } catch (exception: Exception) {
            Log.e(TAG, "Exception calling getLocalAuthorities", exception)
            Result.failure(exception)
        }
    }

    // Get list of establishments from API
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
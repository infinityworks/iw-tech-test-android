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

    companion object {
        private const val TAG = "REPO"
    }
}
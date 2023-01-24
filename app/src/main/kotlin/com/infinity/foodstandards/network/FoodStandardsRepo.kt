package com.infinity.foodstandards.network

import android.util.Log
import com.infinity.foodstandards.model.LocalAuthoritiesResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface FoodStandardsRepo {
    suspend fun getLocalAuthorities(): Result<LocalAuthoritiesResponse>
}

class FoodStandardsRepository(
    private var api: FoodStandardsApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : FoodStandardsRepo {

    // Get list of authorities from food standards API
    override suspend fun getLocalAuthorities(): Result<LocalAuthoritiesResponse> = withContext(dispatcher) {
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

package com.infinity.foodstandards.network

import com.infinity.foodstandards.model.LocalAuthoritiesResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface FoodStandardsApi {

    @Headers("x-api-version:2")
    @GET("authorities")
    suspend fun getAuthorities(): LocalAuthoritiesResponse
}

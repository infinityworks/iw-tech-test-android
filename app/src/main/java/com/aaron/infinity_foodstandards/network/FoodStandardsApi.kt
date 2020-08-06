package com.aaron.infinity_foodstandards.network

import com.aaron.infinity_foodstandards.model.LocalAuthoritiesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface FoodStandardsApi {

    @Headers("x-api-version:2")
    @GET("authorities")
    fun getAuthorities(): Call<LocalAuthoritiesResponse>

}
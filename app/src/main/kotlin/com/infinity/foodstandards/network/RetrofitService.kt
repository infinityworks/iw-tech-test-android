package com.infinity.foodstandards.network

import com.infinity.foodstandards.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    companion object {
        fun createService(): FoodStandardsApi {
            val client = OkHttpClient().newBuilder()

            //Just use the interceptor in debug config
            if (BuildConfig.MOCK) {
                client.interceptors().add(MockInterceptor())
            }

            return Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build()
                .create(FoodStandardsApi::class.java)
        }
    }
}
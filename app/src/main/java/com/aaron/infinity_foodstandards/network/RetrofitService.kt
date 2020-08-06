package com.aaron.infinity_foodstandards.network

import com.aaron.infinity_foodstandards.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    companion object {
        fun createService(): FoodStandardsApi {
            val client: OkHttpClient.Builder

            //Just use the interceptor in debug config
            if (BuildConfig.MOCK) {
                client = OkHttpClient().newBuilder()
                client.interceptors().add(MockInterceptor())
            } else {
                client = OkHttpClient().newBuilder()
            }

            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build()
            return retrofit.create(FoodStandardsApi::class.java)
        }
    }
}
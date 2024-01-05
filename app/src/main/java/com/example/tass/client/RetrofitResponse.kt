package com.example.tass.client

import com.example.tass.apiinterface.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitResponse {
    private const val baseUrl = "https://app.getswipe.in/api/public/"

    private fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiInterface: ApiInterface = getInstance().create(ApiInterface::class.java)
}

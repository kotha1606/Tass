package com.example.tass.apiinterface

import com.example.tass.data.Postresponse
import com.example.tass.data.Productitem
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @GET("get")
    suspend fun getresponse(): Response<List<Productitem>>

    @FormUrlEncoded
    @POST("add")
    suspend fun postresponse(
        @Field("image") image: String,
        @Field("price") price: Double,
        @Field("product_name") product_name: String,
        @Field("product_type") product_type: String,
        @Field("tax") tax: Double
    ): Response<Postresponse>



}
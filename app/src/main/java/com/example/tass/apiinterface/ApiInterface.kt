package com.example.tass.apiinterface

import com.example.tass.data.AddProductResponse
import com.example.tass.data.Product
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @GET("get")
    suspend fun getresponse(): Response<List<Product>>

//    @FormUrlEncoded
//    @POST("add")
//    suspend fun postresponse(@Body name:Productitem):Response<Postresponse>
    @FormUrlEncoded
    @POST("add")
    suspend fun postresponse(
    @Field("price") price : String,
    @Field("product_name") product_name : String,
    @Field("product_type") product_type: String,
    @Field("tax") tax : String,
    ):Response<AddProductResponse>


}
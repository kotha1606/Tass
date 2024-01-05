package com.example.tass.data

data class Postresponse(
    val message: String,
    val product_details: Productitem,
    val product_id: Int,
    val success: Boolean
)
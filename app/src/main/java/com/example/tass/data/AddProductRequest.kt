package com.example.tass.data

    data class AddProductRequest(
        val product_name: String,
        val product_type: String,
        val price: String,
        val tax: String
    )


package com.example.tass.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.tass.R
import com.example.tass.client.RetrofitResponse
import com.example.tass.databinding.ActivityAddBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_add)

        val addProductName=binding.productName.text.toString()
        val addProductTitle=binding.productTitle.text.toString()
        val addProductPrice=binding.price.text.toString()
        val addProductTax=binding.tax.text.toString()


        binding.post.setOnClickListener {

            GlobalScope.launch {
//
                val response = RetrofitResponse.apiInterface.postresponse(addProductPrice,addProductName,addProductTitle,addProductTax)

                runOnUiThread {
                    response.body()?.let {
                        Log.d("ErrorTAG",it.message)
//                        Log.d("ErrorTAG",it.success)
//                        Log.d("ErrorTAG",it.product_id)
//                        Log.d("ErrorTAG",it.product_details)
//

                    }
                }
            }
        }


    }
}
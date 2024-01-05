package com.example.tass.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tass.client.RetrofitResponse
import com.example.tass.databinding.ActivityAddBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.post.setOnClickListener {

            val addProductName = binding.productName.text.toString()
            val addProductTitle = binding.productTitle.text.toString()
            val addProductPrice = binding.price.text.toString()
            val addProductTax = binding.tax.text.toString()


            GlobalScope.launch {
                Log.d("myApiTag", "clicked btn")

                try {
                    val response = RetrofitResponse.apiInterface.postresponse(
                        "hello",
                        addProductPrice.toDouble(),
                        addProductName,
                        addProductTitle,
                        addProductTax.toDouble()
                    )

                    runOnUiThread {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                Toast.makeText(this@AddActivity, "${it.message}", Toast.LENGTH_LONG).show()
                                Log.d("myApiTag", "${it.message}")
                                finish()

                            }
                        } else {
                            // Check the response code and handle accordingly
                            Toast.makeText(
                                this@AddActivity,
                                "Failed to add product. Response code: ${response.code()}",
                                Toast.LENGTH_LONG
                            ).show()
                            Log.d("myApiTag", "${response.code()}")

                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    runOnUiThread {
                        Toast.makeText(this@AddActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                        Log.d("myApiTag", "${e.message}")

                    }
                }
            }

        }

    }
}
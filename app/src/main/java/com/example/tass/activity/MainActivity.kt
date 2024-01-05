package com.example.tass.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tass.adapter.MyAdapter
import com.example.tass.client.RetrofitResponse
import com.example.tass.data.Product
import com.example.tass.databinding.ActivityMainBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MyAdapter
    private lateinit var tempList: List<Product>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        callApi()

        //adding feature calling intent
        binding.add.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }

        //Adapter initialization
        adapter = MyAdapter(this, emptyList())
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = adapter

    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun callApi() {
        setInProgress(true)
        GlobalScope.launch {
            val response = RetrofitResponse.apiInterface.getresponse()
            runOnUiThread {
                setInProgress(false)
                response.body()?.let { productList ->
//                    Log.i("APITAG",it.toString())
                    tempList = productList
                    setUi(tempList)
                    binding.searchView.setOnQueryTextListener(object :
                        SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            TODO("Not yet implemented")
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {
                            tempList = emptyList()
                            val searchText = newText!!.lowercase(Locale.getDefault())
                            if (searchText.isNotEmpty()) {
                                productList.forEach {
                                    if (it.product_name.lowercase(Locale.getDefault()).contains(searchText)
                                    ) {
                                        tempList= listOf(it)
                                    }
                                }
                                setUi(tempList)
                            }else{

                                tempList= emptyList()
                                tempList=productList
                                setUi(tempList)
                            }

                            return false
                        }
                    })

                }

            }
        }
    }


    private fun setUi(it: List<Product>) {
        adapter.updateData(it)
    }

    private fun setInProgress(b: Boolean) {
        if (b) {
            binding.progressBar.visibility = View.VISIBLE
            binding.rv.visibility = View.INVISIBLE
        } else {
            binding.progressBar.visibility = View.INVISIBLE
            binding.rv.visibility = View.VISIBLE
        }
    }
}

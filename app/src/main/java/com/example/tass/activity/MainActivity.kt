package com.example.tass.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tass.adapter.MyAdapter
import com.example.tass.client.RetrofitResponse
import com.example.tass.databinding.ActivityMainBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MyAdapter


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
        adapter = MyAdapter(this)
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = adapter


        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener  {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(searchViewText: String?): Boolean {
                if(searchViewText != null){
                    adapter.searchList(searchViewText)
                }
                return true
            }

        })

    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun callApi() {
        setInProgress(true)
        GlobalScope.launch {
            val response = RetrofitResponse.apiInterface.getresponse()
            runOnUiThread {
                setInProgress(false)
                response.body()?.let { productList ->
                    adapter.setData(productList)
                }

            }
        }
    }


    override fun onResume() {
        super.onResume()
        callApi()
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

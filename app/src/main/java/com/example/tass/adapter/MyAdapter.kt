package com.example.tass.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tass.data.Product
import com.example.tass.databinding.CustomLayoutBinding

class MyAdapter(val context:Context,private var productList:List<Product>):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    inner class MyViewHolder(private val binding: CustomLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

            fun bind(Product: Product) {
                
                binding.productName.text=Product.product_name
                binding.productType.text=Product.product_type
                binding.price.text=Product.price.toString()
                binding.tax.text=Product.tax.toString()
                Glide.with(context).load(Product.image).into(binding.imageView)
            }
        }
        @SuppressLint("NotifyDataSetChanged")
        fun updateData(newProductList:List<Product>) {
            productList = newProductList
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val binding = CustomLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return MyViewHolder(binding)
        }

        override fun getItemCount(): Int {
            return productList.size
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.bind(productList[position])
        }

    }

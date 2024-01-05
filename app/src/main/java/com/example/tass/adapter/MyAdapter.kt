package com.example.tass.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tass.R
import com.example.tass.data.Productitem
import com.example.tass.databinding.CustomLayoutBinding

class MyAdapter(val context:Context):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var tempList = ArrayList<Productitem>()
    private var productList:ArrayList<Productitem> = ArrayList()

    inner class MyViewHolder(private val binding: CustomLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(productitem: Productitem) {

            binding.productName.text=productitem.product_name
            binding.productType.text=productitem.product_type
            binding.price.text=productitem.price.toString()
            binding.tax.text=productitem.tax.toString()
            Glide.with(context).load(productitem.image)
                .placeholder(R.drawable.placeholder_image)
                .into(binding.imageView)
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setData(newProductList:List<Productitem>) {
        tempList.clear()
        tempList.addAll(newProductList)

        productList.clear()
        productList.addAll(tempList)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun searchList(searchViewText: String) {

        productList.clear()

        for (item in tempList) {

            if (item.product_name?.contains(searchViewText, true) == true ||
                item.product_type?.contains(searchViewText, true) == true
            ) {
                productList.add(item)
            }
        }
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

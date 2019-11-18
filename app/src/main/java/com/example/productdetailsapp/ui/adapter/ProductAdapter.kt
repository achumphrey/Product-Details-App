package com.example.productdetailsapp.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.productdetailsapp.R
import com.example.productdetailsapp.data.ProductModel
import com.example.productdetailsapp.ui.viewholder.ProductViewHolder
import com.example.productdetailsapp.utils.inflate


class ProductAdapter constructor(private val prodDetail: List<ProductModel>, private val listener: ProductListener) :
    RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        val view: View = parent.inflate(R.layout.view_holder_product, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount() = prodDetail.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindItem(prodDetail[holder.adapterPosition],listener)
    }
}

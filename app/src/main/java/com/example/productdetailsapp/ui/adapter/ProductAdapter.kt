package com.example.productdetailsapp.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.productdetailsapp.R
import com.example.productdetailsapp.data.ProductModel
import com.example.productdetailsapp.ui.viewholder.ProductViewHolder
import com.example.productdetailsapp.utils.inflate


class ProductAdapter constructor(private val prodDetail: List<ProductModel>, private val listener: ProductListener, var selectedProd: ProductModel?=null) :
    RecyclerView.Adapter<ProductViewHolder>() {

    val list = arrayListOf<ProductModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        val view: View = parent.inflate(R.layout.view_holder_product, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount() = prodDetail.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindItem(prodDetail[holder.adapterPosition],listener, selectedProd){prodDetail ->
            selectedProd = prodDetail}
    }

/* override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
   holder.bindItem(prodDetail[holder.adapterPosition], selectedProd ){prodDetail ->
        selectedProd = prodDetail
       getSelectProdList()
   }
}

fun getSelectProdList() {
   for (i in 0 until prodDetail.size) {
       if (prodDetail[i] == selectedProd)
           list.add(prodDetail[i])
   }
}       selectedProd = prodDetail


fun getselectList(): ArrayList<ProductModel> {
   if (list.isEmpty() ){
       return arrayListOf()
   }else
       return list
}*/

}

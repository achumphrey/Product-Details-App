package com.example.productdetailsapp.ui.adapter

import android.view.View
import com.example.productdetailsapp.data.ProductModel

interface ProductListener {
    fun onProdClick(prodDetail: ProductModel){
    }
}
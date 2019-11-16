package com.example.productdetailsapp.data.repository

import com.example.productdetailsapp.data.ProductModel
import io.reactivex.Single

interface ProductRepository {
    fun fetchProductRepos(): Single<List<ProductModel>>
}
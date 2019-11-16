package com.example.productdetailsapp.data.remote

import com.example.productdetailsapp.data.ProductModel
import com.example.productdetailsapp.utils.Constant
import io.reactivex.Single
import retrofit2.http.GET


interface ProductWebServices {

    @GET(Constant.endpointUrl)
    fun fetchProdWebService(): Single<List<ProductModel>>
}
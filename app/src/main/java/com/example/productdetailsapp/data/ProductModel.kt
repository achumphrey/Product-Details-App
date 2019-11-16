package com.example.productdetailsapp.data


import com.google.gson.annotations.SerializedName

data class ProductModel(
    @SerializedName("descr")
    val descr: String,
    @SerializedName("mfgDate")
    val mfgDate: String,
    @SerializedName("pId")
    val pId: String,
    @SerializedName("price")
    val price: Price,
    @SerializedName("productName")
    val productName: String
)
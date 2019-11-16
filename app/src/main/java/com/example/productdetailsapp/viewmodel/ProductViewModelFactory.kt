package com.example.productdetailsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.productdetailsapp.data.repository.ProductRepositoryImpl

@Suppress("UNCHECKED_CAST")
class ProductViewModelFactory constructor(private val productRepository: ProductRepositoryImpl): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductViewModel(productRepository) as T
    }
}
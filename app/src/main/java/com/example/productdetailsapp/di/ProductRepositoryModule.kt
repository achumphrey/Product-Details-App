package com.example.productdetailsapp.di

import com.example.productdetailsapp.data.repository.ProductRepository
import com.example.productdetailsapp.data.repository.ProductRepositoryImpl
import com.example.productdetailsapp.viewmodel.ProductViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ProductRepositoryModule {
    @Provides
    @Singleton
    fun provideProductViewModelFactory(productRepository: ProductRepositoryImpl): ProductViewModelFactory{
        return ProductViewModelFactory(productRepository)
    }
}
package com.example.productdetailsapp.di

import com.example.productdetailsapp.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ProductWebservicesModule::class, ProductRepositoryModule::class])
interface ProductComponent {

    fun inject(mainActivity: MainActivity)
}
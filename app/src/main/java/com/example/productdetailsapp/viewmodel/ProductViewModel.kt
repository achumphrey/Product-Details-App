package com.example.productdetailsapp.viewmodel

import android.app.Activity
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.productdetailsapp.data.ProductModel
import com.example.productdetailsapp.data.repository.ProductRepository
import com.example.productdetailsapp.ui.MainActivity
import io.reactivex.disposables.CompositeDisposable
import java.net.UnknownHostException

class ProductViewModel constructor(private val productRepository: ProductRepository) : ViewModel() {
    private val disposable = CompositeDisposable()


    fun fetchProdDetails() {
        loadingState.value = LoadingState.LOADING

        disposable.add(
            productRepository.fetchProductRepos()
                 .subscribe({
                    if (it.isEmpty()) {
                        errorMessage.value = "No Data Found"
                        loadingState.value = LoadingState.ERROR
                    } else {
                        prod.value = it
                        loadingState.value = LoadingState.SUCCESS
                        Log.i("ViewModel", "${it[0].productName}")
                    }
                 }, {
                    it.printStackTrace()

                        when (it) {
                            is UnknownHostException -> errorMessage.value = "No Network"
                            else -> errorMessage.value = it.localizedMessage
                        }
                        loadingState.value = LoadingState.ERROR
                 })
        )
    }

    val prod: MutableLiveData<List<ProductModel>> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val loadingState = MutableLiveData<LoadingState>()

    enum class LoadingState {
        LOADING,
        SUCCESS,
        ERROR
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    fun getActivity(): Class<out Activity> {
        return MainActivity::class.java
    }

}
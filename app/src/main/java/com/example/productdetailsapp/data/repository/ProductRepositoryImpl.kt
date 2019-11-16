package com.example.productdetailsapp.data.repository
import com.example.productdetailsapp.data.ProductModel
import com.example.productdetailsapp.data.remote.ProductWebServices
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class ProductRepositoryImpl @Inject constructor(private val webServices: ProductWebServices) : ProductRepository {
    override fun fetchProductRepos(): Single<List<ProductModel>> {
        return webServices.fetchProdWebService()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
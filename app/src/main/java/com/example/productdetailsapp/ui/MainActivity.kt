package com.example.productdetailsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productdetailsapp.R.layout.activity_main
import com.example.productdetailsapp.data.ProductModel
import com.example.productdetailsapp.di.DaggerProductComponent
import com.example.productdetailsapp.di.ProductRepositoryModule
import com.example.productdetailsapp.di.ProductWebservicesModule
import com.example.productdetailsapp.ui.adapter.ProductAdapter
import com.example.productdetailsapp.ui.adapter.ProductListener
import com.example.productdetailsapp.viewmodel.ProductViewModel
import com.example.productdetailsapp.viewmodel.ProductViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var productViewModelFactory: ProductViewModelFactory
    private lateinit var viewModel: ProductViewModel
    private lateinit var productAdapter: ProductAdapter
    private var list = arrayListOf<ProductModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)

        DaggerProductComponent.builder()
            .productWebservicesModule(ProductWebservicesModule())
            .productRepositoryModule(ProductRepositoryModule())
            .build()
            .inject(this)


        viewModel =
            ViewModelProviders.of(this, productViewModelFactory).get(ProductViewModel::class.java)
        viewModel.fetchProdDetails()
        viewModel.prod.observe(this, Observer {

            productAdapter = ProductAdapter(
                it,
                object :
                    ProductListener {
                    override fun onProdClick(prodDetail: ProductModel) {
                        list.add(prodDetail)
                    }
                }
            )
            rvProd.layoutManager = LinearLayoutManager(this)
            rvProd.adapter = productAdapter
        })

        btnCompare.setOnClickListener {
            var textToDisplay = ""

            if (list.size == 2) {
                if (list[0].pId == list[1].pId && list[0].price == list[1].price) {
                    textToDisplay = "Both Products are the same"
                } else {
                    textToDisplay = "Both Products are different"
                }
            }else if  (list.size > 2) {
                textToDisplay = "Only two products can be selected"

            }else if (list.size < 2 && list.isNotEmpty()){
                textToDisplay = "You have less than two products"

            }else if (list.isEmpty())
                textToDisplay = "List is Empty"

            tvDisplay.text = textToDisplay

            list.clear()
        }

        btnReset.setOnClickListener {
             tvDisplay.text = ""
        }
    }
}

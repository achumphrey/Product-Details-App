package com.example.productdetailsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productdetailsapp.R
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
import kotlinx.android.synthetic.main.view_holder_product.*
import javax.inject.Inject
import kotlin.math.log

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

        viewModel = ViewModelProviders.of(this, productViewModelFactory).get(ProductViewModel::class.java)
        viewModel.fetchProdDetails()
        viewModel.prod.observe(this, Observer {

            Log.i("MainActivity", "${it[1].productName}")
            productAdapter = ProductAdapter(it, object :
                ProductListener{
                override fun onProdClick(prodDetail: ProductModel, v: View) {
                    if (cbProd.isChecked){
                       list.add(prodDetail)
                    }
                }
            })

            rvProd.layoutManager = LinearLayoutManager(this)
            rvProd.adapter = productAdapter
        })

        btnCompare.setOnClickListener {
            var textToDisplay: String
            if(list.isEmpty()){
                textToDisplay = "Empty List"
            }else{
            textToDisplay = list?.size.toString()
            }
            tvDisplay.text = textToDisplay
            Log.i("MainActivity", "${list?.get(0)?.productName}")
        }

        btnReset.setOnClickListener {
            tvDisplay.text = ""
            list?.clear()
          if (cbProd.isChecked)
            cbProd.isChecked = false
        }
    }
}

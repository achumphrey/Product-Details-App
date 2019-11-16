package com.example.productdetailsapp.ui.viewholder
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.productdetailsapp.data.ProductModel
import com.example.productdetailsapp.ui.adapter.ProductListener
import kotlinx.android.synthetic.main.view_holder_product.view.*


class ProductViewHolder(item: View) : RecyclerView.ViewHolder(item) {
    fun bindItem(
        prodDetails: ProductModel,
        listener:ProductListener,
        selectedProd: ProductModel?,
        prodClicked: (productDetail: ProductModel) -> Unit) {

        itemView.tvProdId.text = prodDetails.pId
        itemView.tvPrice.text = prodDetails.price.amount.rate
        itemView.tvProdName.text = prodDetails.productName
        itemView.tvProdDesc.text = prodDetails.descr
        itemView.tvMfgDate.text = prodDetails.mfgDate

        itemView.cbProd.isChecked = prodDetails == selectedProd

        itemView.setOnClickListener {
            prodClicked(prodDetails)
        }

        itemView.cbProd.setOnClickListener {
            prodClicked(prodDetails)
        }

        itemView.setOnClickListener {
            listener.onProdClick(prodDetails, it)
        }
    }
}
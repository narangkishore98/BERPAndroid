package com.bikloo.berpandroid.adapters

import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bikloo.berpandroid.Classes.Product
import com.bikloo.berpandroid.R
import kotlinx.android.synthetic.main.product_list_layout.view.*

class ProductAdapter(val products:MutableList<Product>): RecyclerView.Adapter<ProductViewHolder>()
{
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ProductViewHolder {

        val view = LayoutInflater.from(p0.context).inflate(R.layout.product_list_layout, p0, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(p0: ProductViewHolder, p1: Int) {
        p0.textBarcode.text = products[p1].barcode
        p0.textProductName.text = products[p1].name

    }

}

class ProductViewHolder(val view: View) : RecyclerView.ViewHolder(view)
{

    var textProductName = view.productName
    var textBarcode = view.barcode


}

package com.bikloo.berpandroid

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.bikloo.berpandroid.Classes.Product
import com.bikloo.berpandroid.DataBase.DataStore
import kotlinx.android.synthetic.main.activity_add_product_from_barcode.*

class AddProductFromBarcodeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product_from_barcode)
        txtBarcode.text = intent.getStringExtra("barcode")!!
        var x = arrayListOf<String>(
            "Food","Bakery","Organic","Dairy","Beverages"

        )
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, x)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnrProductType.adapter = adapter
        //adding functionality for btn add product

        btnAddProduct.setOnClickListener(View.OnClickListener {
            //Toast.makeText(this, "Button Is Working",Toast.LENGTH_SHORT).show()
            val productName = edtProductName.text.toString()
            val productDescripton = edtDescription.text.toString()
            val price = edtPrice.text.toString().toFloat()
            val productType = getType(x[spnrProductType.selectedItemPosition])

            //var p = Product(productName, txtBarcode.text.toString(), )
            var product = Product(productName, txtBarcode.text.toString(), productDescripton, price, productType)
            DataStore.selectedEnterprise!!.products.add(product)
            Toast.makeText(this,"Product Saved",Toast.LENGTH_SHORT).show()

        })
    }
    fun getType( productType:String) : Product.ProductType
    {
        when(productType)
        {
            "Food" ->   Product.ProductType.Food
            "Bakery" -> Product.ProductType.Bakery
            "Dairy" -> Product.ProductType.Dairy
            "Organic" -> Product.ProductType.Organic
            "Beverages" -> Product.ProductType.Beverages
            else ->
            {
                return Product.ProductType.Food
            }

        }
        return Product.ProductType.Food
    }
    companion object
    {
        fun open(withContext: Context, withData:Bundle?)

        {
            var i: Intent =  Intent(withContext, AddProductFromBarcodeActivity::class.java)
            if(withData != null)
            {
                i.putExtras(withData)
            }
            withContext.startActivity(i)

        }
    }
}

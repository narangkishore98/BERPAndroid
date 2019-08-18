package com.bikloo.berpandroid

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.bikloo.berpandroid.Classes.Product
import com.bikloo.berpandroid.DataBase.DataStore
import kotlinx.android.synthetic.main.activity_add_product_from_barcode.*

class AddProductFromBarcodeActivity : AppCompatActivity() {


    fun showAlert(message:String, activity: Activity)
    {

        val rootView = activity.window.decorView.findViewById<View>(android.R.id.content)
        Snackbar.make(rootView, message, Snackbar.LENGTH_LONG).show()
        // val mySnackbar = Snackbar.make(findViewById(R.id.myID), "No Employees Added yet.", Snackbar.LENGTH_SHORT)
    }

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
            if(checkProductIfExistsWithBarcode(txtBarcode.text.toString()))
            {
                showAlert("Product Already Exist!", this)
                finish()
            }
            else {
            val productName = edtProductName.text.toString()
            val productDescripton = edtDescription.text.toString()
            val price = edtPrice.text.toString().toFloat()
            val productType = getType(x[spnrProductType.selectedItemPosition])

            //var p = Product(productName, txtBarcode.text.toString(), )
            var product = Product(productName, txtBarcode.text.toString(), productDescripton, price, productType)

                DataStore.selectedEnterprise!!.products.add(product)
                Toast.makeText(this, "Product Saved", Toast.LENGTH_SHORT).show()

                var builder = AlertDialog.Builder(this).setTitle("Task Completed")
                    .setMessage("The Product has been added Successfully. Do You Want to add more products ?")
                builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                    finish()
                }
                builder.setNegativeButton(android.R.string.cancel) { dialog, which ->
                    EnterpriseDetailActivity.open(this, null)
                }
                builder.show()
            }
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
    fun checkProductIfExistsWithBarcode(barcode:String):Boolean
    {
        for(product in DataStore.selectedEnterprise!!.products)
        {
            if(barcode.equals(product.barcode))
            {
                return true
            }
        }
        return false
    }
}

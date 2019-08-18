package com.bikloo.berpandroid

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_product_from_barcode.*

class AddProductFromBarcodeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product_from_barcode)
        txtBarcode.text = intent.getStringExtra("barcode")!!
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

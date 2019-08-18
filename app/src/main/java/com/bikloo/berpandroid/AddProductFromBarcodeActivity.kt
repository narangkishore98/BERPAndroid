package com.bikloo.berpandroid

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
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

        btnAddProduct.setOnClickListener(View.OnClickListener {
            Toast.makeText(this, "Button Is Working",Toast.LENGTH_SHORT).show()
        })
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

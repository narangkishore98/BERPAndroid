package com.bikloo.berpandroid

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.bikloo.berpandroid.DataBase.DataStore
import com.bikloo.berpandroid.adapters.ProductAdapter
import kotlinx.android.synthetic.main.activity_view_product.*

class ViewProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_product)
        productRecyclerView.layoutManager = LinearLayoutManager(this)
        productRecyclerView.adapter = ProductAdapter(DataStore.selectedEnterprise!!.products)

    }
    companion object
    {
        fun open(withContext: Context, withData:Bundle?)

        {
            var i: Intent =  Intent(withContext, ViewProductActivity::class.java)
            if(withData != null)
            {
                i.putExtras(withData)
            }
            withContext.startActivity(i)

        }
    }
}

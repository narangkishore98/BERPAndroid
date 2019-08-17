package com.bikloo.berpandroid

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class DashboardActiviy : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_activiy)

        this.supportActionBar!!.title = "Dashboard"

    }
    companion object
    {
        fun open(withContext: Context, withData:Bundle?)

        {
            var i:Intent =  Intent(withContext, DashboardActiviy::class.java)
            if(withData != null)
            {
                i.putExtras(withData)
            }
            withContext.startActivity(i)

        }
    }

}

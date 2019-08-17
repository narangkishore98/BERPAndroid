package com.bikloo.berpandroid

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu

class DashboardActiviy : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_activiy)

        this.supportActionBar!!.title = "Dashboard"

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        this.menuInflater.inflate(R.menu.menu_for_dashboard, menu)
        return true
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

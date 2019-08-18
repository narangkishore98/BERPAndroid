package com.bikloo.berpandroid

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.bikloo.berpandroid.Classes.Enterprise
import com.bikloo.berpandroid.adapters.ViewEmployeeAdapter
import kotlinx.android.synthetic.main.activity_view_employee.*

class ViewEmployeeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_employee)
        supportActionBar!!.title = "View Employees"
        recyclerViewEmployeeDetails.layoutManager = LinearLayoutManager(this)
        var employees = (intent.getSerializableExtra("selectedEnterprise") as Enterprise).employees
        recyclerViewEmployeeDetails.adapter = ViewEmployeeAdapter(employees)


    }
    companion object
    {
        fun open(withContext: Context, withData:Bundle?)

        {
            var i: Intent =  Intent(withContext, ViewEmployeeActivity::class.java)
            if(withData != null)
            {
                i.putExtras(withData)
            }
            withContext.startActivity(i)

        }
    }
}

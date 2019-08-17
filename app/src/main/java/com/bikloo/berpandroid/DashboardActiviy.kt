package com.bikloo.berpandroid

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import com.bikloo.berpandroid.Classes.Enterprise
import com.bikloo.berpandroid.adapters.EnterpriseAdapter
import kotlinx.android.synthetic.main.activity_dashboard_activiy.*

class DashboardActiviy : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_activiy)

        enterprisesRecyclerView.layoutManager = LinearLayoutManager(this)
        this.supportActionBar!!.title = "Dashboard"

        var list:ArrayList<Enterprise> = ArrayList()
           list.add(            Enterprise("Tim Hortons","New Address", Enterprise.EnterpriseType.Restaurant)
               )
            list.add(Enterprise("McDonalds","Old Address", Enterprise.EnterpriseType.Restaurant))

            list.add(                Enterprise("Dominos","New Address", Enterprise.EnterpriseType.Restaurant)
            )

        enterprisesRecyclerView.adapter = EnterpriseAdapter(list)


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

package com.bikloo.berpandroid

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.bikloo.berpandroid.Classes.Enterprise
import com.bikloo.berpandroid.DataBase.DataStore
import com.bikloo.berpandroid.adapters.EnterpriseAdapter
import kotlinx.android.synthetic.main.activity_dashboard_activiy.*

class DashboardActiviy : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_activiy)

        enterprisesRecyclerView.layoutManager = LinearLayoutManager(this)
        this.supportActionBar!!.title = "Dashboard"

//        var list:ArrayList<Enterprise> = ArrayList()
//           list.add(            Enterprise("Tim Hortons","New Address", Enterprise.EnterpriseType.Restaurant)
//               )
//            list.add(Enterprise("McDonalds","Old Address", Enterprise.EnterpriseType.Restaurant))
//
//            list.add(                Enterprise("Dominos","New Address", Enterprise.EnterpriseType.Restaurant)
//            )
//


//        enterprisesRecyclerView.adapter = EnterpriseAdapter(list)

        if(DataStore.selectedOwner!!.myEnterprise.size == 0)
        {
            var builder = AlertDialog.Builder(this).setTitle("Welcome")
                .setMessage("You are here for first time. To access the services please add your first enterprise.")
            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                AddEnterpriseActivity.open(this,null)
            }
            builder.setNegativeButton("Logout") { dialog, which ->
                finish()
            }
            builder.show()
        }
        else
        {
            var list:ArrayList<Enterprise> = ArrayList()
            for(enterprise in DataStore.selectedOwner!!.myEnterprise)
            {
                list.add(enterprise)
            }
            enterprisesRecyclerView.adapter = EnterpriseAdapter(list)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        this.menuInflater.inflate(R.menu.menu_for_dashboard, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        //Toast.makeText(this,"ITEM SELECTED = ${item!!.itemId} MENU ITEM = ${R.id.menuItemLogout}", Toast.LENGTH_LONG).show()

        when(item!!.itemId)
        {

            R.id.menuItemLogout ->
            {
                Toast.makeText(this,"Logout",Toast.LENGTH_LONG).show()
                finish()
            }
            R.id.menuItemAddEnterprise ->
            {
                AddEnterpriseActivity.open(this,null)
            }
        }
        return super.onOptionsItemSelected(item)
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

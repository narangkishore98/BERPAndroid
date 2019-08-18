package com.bikloo.berpandroid

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Toast
import com.bikloo.berpandroid.Classes.Enterprise
import kotlinx.android.synthetic.main.activity_enterprise_detail.*
import android.R.attr.duration
import android.R.id.message
import android.app.Activity
import com.bikloo.berpandroid.DataBase.DataStore


class EnterpriseDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enterprise_detail)
        var selectedEnterprise = DataStore.selectedEnterprise!!
        if(selectedEnterprise!=null)
        {
            txtDtlEnterpriseName.text = selectedEnterprise.enterpriseName
            txtDtlBonusMultiplier.text = "${selectedEnterprise.bonusMultiplier}"
            txtDtlEnterpriseAddress.text = selectedEnterprise.address
            txtDtlEnterpriseType.text = "${selectedEnterprise.enterpriseType}"
        }
        //adding functionality for FAB add Employee.
        addEmployeeFab.setOnClickListener(View.OnClickListener {

            var bundle = Bundle()
            bundle.putSerializable("selectedEnterprise",selectedEnterprise)
            AddEmployeeActivity.open(this,bundle)
            Toast.makeText(this, "Adding Employee",Toast.LENGTH_SHORT).show()
        })
        //adding functionality for FAB view employees.
        viewEmployeeFab.setOnClickListener(View.OnClickListener {
            if(selectedEnterprise!!.employees.size == 0)
            {
                showAlert("No Employees Exist", this)
            }
            else
            {
                var bundle = Bundle()
                bundle.putSerializable("selectedEnterprise",selectedEnterprise)
                ViewEmployeeActivity.open(this,bundle)
                Toast.makeText(this, "View Employee",Toast.LENGTH_SHORT).show()
            }
        })
    }
    fun showAlert(message:String, activity: Activity)
    {

        val rootView = activity.window.decorView.findViewById<View>(android.R.id.content)
        Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show()
       // val mySnackbar = Snackbar.make(findViewById(R.id.myID), "No Employees Added yet.", Snackbar.LENGTH_SHORT)
    }
    companion object
    {
        fun open(withContext: Context, withData:Bundle?)

        {
            var i: Intent =  Intent(withContext, EnterpriseDetailActivity::class.java)
            if(withData != null)
            {
                i.putExtras(withData)
            }
            withContext.startActivity(i)

        }
    }

}

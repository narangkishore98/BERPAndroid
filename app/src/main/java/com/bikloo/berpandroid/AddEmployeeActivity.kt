package com.bikloo.berpandroid

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bikloo.berpandroid.Classes.Employee
import com.bikloo.berpandroid.Classes.Enterprise
import com.bikloo.berpandroid.DataBase.DataStore
import kotlinx.android.synthetic.main.activity_add_employee.*
import java.util.*
import kotlin.random.Random

class AddEmployeeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_employee)
        supportActionBar!!.title = "Add Employee"
        btnAddEmployee.setOnClickListener(View.OnClickListener {
            var selectedEnterprise = intent.extras.getSerializable("selectedEnterprise") as Enterprise?
            val empEmail = edtEmpEmail.text.toString()
            val empName = edtEmpName.text.toString()
            val empAddress = edtEmpAddress.text.toString()

            Log.d("Data","$empAddress , $empEmail , $empName")
            val newEmployeeObject = Employee(empEmail!!, empName!!,"${Random.nextInt(1000,9999)}" , empAddress!! )
            DataStore.selectedEnterprise!!.employees.add(newEmployeeObject)

            var bundle = Bundle()

            bundle.putSerializable("enterprise",selectedEnterprise!!)

            EnterpriseDetailActivity.open(this,bundle)
        })
    }
    companion object
    {
        fun open(withContext: Context, withData:Bundle?)

        {
            var i: Intent =  Intent(withContext, AddEmployeeActivity::class.java)
            if(withData != null)
            {
                i.putExtras(withData)
            }
            withContext.startActivity(i)

        }
    }
}

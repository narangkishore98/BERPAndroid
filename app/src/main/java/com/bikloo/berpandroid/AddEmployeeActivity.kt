package com.bikloo.berpandroid

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bikloo.berpandroid.Classes.Employee
import com.bikloo.berpandroid.Classes.Enterprise
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
            val newEmployeeObject = Employee(edtEmpEmail.text.toString(), edtEmpName.text.toString(),"${Random.nextInt(1000,9999)}" , edtEmpAddress.text.toString() )
            selectedEnterprise!!.employees.add(newEmployeeObject)

            var bundle = Bundle()

            bundle.putSerializable("selectedEnterprise",selectedEnterprise!!)

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

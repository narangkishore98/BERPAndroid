package com.bikloo.berpandroid

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.bikloo.berpandroid.Classes.Enterprise
import com.bikloo.berpandroid.Classes.Product
import com.bikloo.berpandroid.DataBase.DataStore
import kotlinx.android.synthetic.main.activity_add_enterprise.*

class AddEnterpriseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_enterprise)

        var typeList = arrayListOf<String>("Restaurant","Store","MegaMart")
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, typeList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnrEnterpriseType.adapter = adapter

        btnAddEnterprise.setOnClickListener(View.OnClickListener {
            val enterpriseName = edtEnterpriseName.text.toString()
            val enterpriseAddress = edtAddress.text.toString()
            val enterpriseBonusMultiplier = edtBonusMultiplier.text.toString()
            val enterpriseType = getType(typeList[spnrEnterpriseType.selectedItemPosition])

            var enterprise = Enterprise(enterpriseName!!, enterpriseAddress!! , enterpriseType)
            enterprise.bonusMultiplier = enterpriseBonusMultiplier.toInt()

            DataStore.selectedOwner!!.addEnterprise(enterprise)

        })


    }
    fun getType( productType:String) : Enterprise.EnterpriseType
    {
        when(productType)
        {
            "Restaurant" ->   Enterprise.EnterpriseType.Restaurant
            "Store" -> Enterprise.EnterpriseType.Store
            "MegaMart" -> Enterprise.EnterpriseType.MegaMart

            else ->
            {
                return Enterprise.EnterpriseType.Restaurant
            }

        }
        return Enterprise.EnterpriseType.Restaurant
    }
    companion object
    {
        fun open(withContext: Context, withData:Bundle?)

        {
            var i: Intent =  Intent(withContext, AddEnterpriseActivity::class.java)
            if(withData != null)
            {
                i.putExtras(withData)
            }
            withContext.startActivity(i)

        }
    }
}

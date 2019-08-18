package com.bikloo.berpandroid

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bikloo.berpandroid.Classes.Enterprise
import kotlinx.android.synthetic.main.activity_enterprise_detail.*

class EnterpriseDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enterprise_detail)
        var selectedEnterprise = intent.extras.get("enterprise") as Enterprise?
        if(selectedEnterprise!=null)
        {
            txtDtlEnterpriseName.text = selectedEnterprise.enterpriseName
            txtDtlBonusMultiplier.text = "${selectedEnterprise.bonusMultiplier}"
            txtDtlEnterpriseAddress.text = selectedEnterprise.address
            txtDtlEnterpriseType.text = "${selectedEnterprise.enterpriseType}"
        }
        addEmployeeFab.setOnClickListener(View.OnClickListener {

            AddEmployeeActivity.open(this,null)
            Toast.makeText(this, "Adding Employee",Toast.LENGTH_SHORT).show()
        })
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

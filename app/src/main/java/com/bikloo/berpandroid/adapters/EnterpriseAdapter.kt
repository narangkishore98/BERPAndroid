package com.bikloo.berpandroid.adapters

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.bikloo.berpandroid.Classes.Enterprise
import com.bikloo.berpandroid.DataBase.DataStore
import com.bikloo.berpandroid.EnterpriseDetailActivity
import com.bikloo.berpandroid.R
import kotlinx.android.synthetic.main.activity_dashboard_activiy.view.*

class EnterpriseAdapter (val enterprises:ArrayList<Enterprise>) : RecyclerView.Adapter<EnterpriseViewHolder>()
{

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): EnterpriseViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.enterprise_list_layout,p0, false)
        view.setOnClickListener(View.OnClickListener {
            val clickedEnterpriseIndex = p0.enterprisesRecyclerView.getChildLayoutPosition(view)

            Toast.makeText(p0.context, "Taking you to the ${enterprises[clickedEnterpriseIndex].enterpriseName}'s Details", Toast.LENGTH_SHORT).show()
            DataStore.selectedEnterprise = enterprises[clickedEnterpriseIndex]
            var bundle:Bundle = Bundle()
            bundle.putSerializable("enterprise", enterprises[clickedEnterpriseIndex])
            EnterpriseDetailActivity.open(p0.context,bundle)

        })
        return EnterpriseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return enterprises.size
    }

    override fun onBindViewHolder(p0: EnterpriseViewHolder, p1: Int) {
        p0.enterpriseAddress.text = enterprises[p1].address
        p0.enterpriseName.text = enterprises[p1].enterpriseName
    }

}
class EnterpriseViewHolder(val view: View):RecyclerView.ViewHolder(view)
{
    val enterpriseName = view.findViewById<TextView>(R.id.txtEnterpriseName)
    val enterpriseAddress = view.findViewById<TextView>(R.id.txtAddress)
}
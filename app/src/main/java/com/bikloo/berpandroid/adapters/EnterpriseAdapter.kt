package com.bikloo.berpandroid.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bikloo.berpandroid.Classes.Enterprise
import com.bikloo.berpandroid.R

class EnterpriseAdapter (val enterprises:ArrayList<Enterprise>) : RecyclerView.Adapter<EnterpriseViewHolder>()
{
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): EnterpriseViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.enterprise_list_layout,p0, false)
        return EnterpriseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return enterprises.size
    }

    override fun onBindViewHolder(p0: EnterpriseViewHolder, p1: Int) {
        p0.enterpriseAddress.text = enterprises[p1].enterpriseName
        p0.enterpriseName.text = enterprises[p1].address
    }

}
class EnterpriseViewHolder(val view: View):RecyclerView.ViewHolder(view)
{
    val enterpriseName = view.findViewById<TextView>(R.id.txtEnterpriseName)
    val enterpriseAddress = view.findViewById<TextView>(R.id.txtAddress)
}
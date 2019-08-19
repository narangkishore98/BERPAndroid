package com.bikloo.berpandroid.adapters

import android.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bikloo.berpandroid.Classes.Employee
import com.bikloo.berpandroid.R
import kotlinx.android.synthetic.main.activity_view_employee.view.*
import kotlinx.android.synthetic.main.employee_list_layout.view.*

class ViewEmployeeAdapter(val employees:MutableList<Employee>) : RecyclerView.Adapter<ViewEmployeeViewHolder>()
{
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewEmployeeViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.employee_list_layout,p0, false)


        view.setOnClickListener(View.OnClickListener {
            //CREATING A DIALOG FOR DISPLAYING THE INFORMATION OF EMPLOYEE
            val clickedEmployee = p0.recyclerViewEmployeeDetails.getChildLayoutPosition(view)
            Toast.makeText(p0.context,"Employee Details Showing.",Toast.LENGTH_SHORT)
             AlertDialog.Builder(p0.context).setTitle("Employee Details")
                .setMessage("Employee Name: ${employees[clickedEmployee].fullName}\nEmail: ${employees[clickedEmployee].email}\nAddress: ${employees[clickedEmployee].address}\nBonus Points: ${employees[clickedEmployee].points}").show()
        })
        return ViewEmployeeViewHolder(view)
    }

    override fun getItemCount() = employees.size

    override fun onBindViewHolder(p0: ViewEmployeeViewHolder, p1: Int) {
        p0.employeeBonusPoints.text = "Points: ${employees[p1].points}"
        p0.employeeName.text = "${employees[p1].fullName}"
        Log.d("Data",employees[p1].toString())


    }

}
class ViewEmployeeViewHolder(val view: View): RecyclerView.ViewHolder(view)
{
    val employeeName = view.txtEmpDtlName
    val employeeBonusPoints = view.txtEmpDtlBonusPoints
}
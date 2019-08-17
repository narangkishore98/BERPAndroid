package com.bikloo.berpandroid.Classes

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class Employee : User , Serializable
{

    var points: Int = 0
    var workingIn:Enterprise? = null

    constructor(email: String?, fullName: String?, password: String?, address: String?) : super(
        email,
        fullName,
        password,
        UserType.Employee,
        address
    )
    fun addPoints(value:Int)
    {
        points += value
    }
    fun makeOrder(products:MutableList<Product>, discount:Discount?)
    {
        val order = Order(products,this)
        workingIn!!.addOrder(order)
        points += workingIn!!.bonusMultiplier * 10
    }
    override fun toString(): String {
        return super.toString()+" points : $points "
    }



}
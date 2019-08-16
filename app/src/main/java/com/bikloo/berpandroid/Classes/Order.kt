package com.bikloo.berpandroid.Classes

import java.util.*
import kotlin.collections.ArrayList

class Order
{
    companion object
    {
        var ORDER_ID : Int = 0
    }

    var orderID:Int? = null
    var dateTime: Date? = null
    var products:MutableList<Product> = ArrayList()
    var orderMadeBy:Employee? = null
    var totalPrice:Float = 0F

    constructor(products: MutableList<Product>, orderMadeBy: Employee?) {
        this.products = products
        this.orderMadeBy = orderMadeBy
        this.dateTime = Date()
        ORDER_ID +=1
        this.orderID = ORDER_ID
        for (product in products!!)
        {
            this.totalPrice += product.price!!
        }
        this.orderMadeBy = orderMadeBy
    }
}
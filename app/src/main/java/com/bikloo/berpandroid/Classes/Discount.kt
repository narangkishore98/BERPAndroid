package com.bikloo.berpandroid.Classes

class Discount
{
    constructor(discountName: String?, discountCode: String?, discountPercent: Float?) {
        DISCOUNT_ID +=1
        this.discountID = DISCOUNT_ID
        this.discountName = discountName
        this.discountCode = discountCode
        this.discountPercent = discountPercent
    }

    companion object
    {
        var DISCOUNT_ID : Int = 0
    }
    var discountID : Int? = null
    var discountName : String? = null
    var discountCode : String? = null
    var discountPercent : Float? = null

    override fun toString(): String {
        return "Discount(discountID=$discountID, discountName=$discountName, discountCode=$discountCode, discountPercent=$discountPercent)"
    }


}
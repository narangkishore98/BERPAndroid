package com.bikloo.berpandroid.Classes

class Owner :User
{
    var myEnterprise : MutableList<Enterprise> = ArrayList()

    constructor(email: String?, fullName: String?, password: String?, address: String?) : super(
        email,
        fullName,
        password,
        UserType.Owner,
        address
    )

    override fun toString(): String {
        return super.toString()
    }
    fun addEnterprise(enterprise:Enterprise)
    {
        myEnterprise.add(enterprise)
    }
}
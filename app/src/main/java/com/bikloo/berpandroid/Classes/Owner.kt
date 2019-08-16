package com.bikloo.berpandroid.Classes

class Owner :User
{

    constructor(email: String?, fullName: String?, password: String?, userType: UserType?, address: String?) : super(
        email,
        fullName,
        password,
        userType,
        address
    )

    override fun toString(): String {
        return super.toString()
    }
}
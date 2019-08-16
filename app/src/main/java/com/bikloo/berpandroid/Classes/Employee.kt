package com.bikloo.berpandroid.Classes

class Employee :User
{

    var points: Int = 0

    constructor(email: String?, fullName: String?, password: String?, userType: UserType?, address: String?) : super(
        email,
        fullName,
        password,
        userType,
        address
    )
   

}
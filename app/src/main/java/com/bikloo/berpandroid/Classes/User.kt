package com.bikloo.berpandroid.Classes
class User
{
    enum class UserType
    {
        Owner , Employee
    }

    companion object
    {
        var userID : Int = 0
    }
    var userId : Int? = null
    var email:String? = null
    var fullName:String? = null
    var password:String? = null
    var userType:UserType? = null
    var address:String? = null

    constructor(email: String?, fullName: String?, password: String?, userType: UserType?, address: String?) {
        this.email = email
        this.fullName = fullName
        this.password = password
        this.userType = userType
        this.address = address
        userID += 1
        this.userId = userID
    }

    fun doLogin(password:String) : Boolean
    {
        return if(this.password == password) true else false
    }

    override fun toString(): String {
        return "User(userId=$userId, email=$email, fullName=$fullName, password=$password, userType=$userType, address=$address)"
    }


}

package com.bikloo.berpandroid.DataBase

import android.content.Context
import com.bikloo.berpandroid.Classes.User
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.StandardCharsets
import kotlin.collections.ArrayList

class DataStore
{
    var context: Context
    lateinit var userList : ArrayList<User>

    constructor(context: Context) {
        this.context = context
    }

    fun loadJSONFromAsset(): String? {
        val json: String
        try {
            val jsonRef = context.assets.open("Users.json")
            val size = jsonRef.available()
            val buffer = ByteArray(size)
            val count = jsonRef.read(buffer)
            jsonRef.close()
            json = String(buffer, StandardCharsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }

        return json
    }

    fun processJSON() {
        val jsonString = this.loadJSONFromAsset()
        if (jsonString != null) {
            try {
                val mJSONArray = JSONArray(jsonString)
                userList = ArrayList()

                for (i in 0 until mJSONArray.length()) {

                    val mUser : User = getUserObjectFromJSON(mJSONArray.getJSONObject(i))
                    userList.add(mUser)
                    //Log.d("User List Obj ---->",mUser.toString())
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }


    }
    public fun getUserObjectFromJSON(userJsonObject : JSONObject) : User
    {
        val email : String = userJsonObject.getString("email")
        val password : String = userJsonObject.getString("password")
        val address : String = userJsonObject.getString("address")
        val fullName : String = userJsonObject.getString("fullName")
        val userOBJ = User(email,fullName,password,User.UserType.Owner,address)
        //Log.d("User Obj : ->>>>",userOBJ.toString())
        return userOBJ
    }
}
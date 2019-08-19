package com.bikloo.berpandroid.DataBase

import android.content.Context
import android.provider.ContactsContract
import com.bikloo.berpandroid.Classes.Enterprise
import com.bikloo.berpandroid.Classes.User
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.StandardCharsets
import kotlin.collections.ArrayList

class DataStore
{
    lateinit var userList : ArrayList<User>
    companion object
    {

        val instance:DataStore = DataStore()
        var selectedEnterprise:Enterprise? = null
        var selectedUser : User? = null
    }

    fun loadJSONFromAsset(withContext: Context): String? {
        val json: String
        try {
            val jsonRef = withContext.assets.open("Users.json")
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

    fun processJSON(withContext: Context) {
        val jsonString = this.loadJSONFromAsset(withContext)
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
    fun getUserObjectFromJSON(userJsonObject : JSONObject) : User
    {
        val email : String = userJsonObject.getString("email")
        val password : String = userJsonObject.getString("password")
        val address : String = userJsonObject.getString("address")
        val fullName : String = userJsonObject.getString("fullName")
        val userOBJ = User(email,fullName,password,User.UserType.Owner,address)
        //Log.d("User Obj : ->>>>",userOBJ.toString())
        return userOBJ
    }

    override fun toString(): String {
        return "DataStore(userList=${userList.toString()})"
    }


}
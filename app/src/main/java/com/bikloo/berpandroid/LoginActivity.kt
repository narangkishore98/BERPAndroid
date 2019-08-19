package com.bikloo.berpandroid

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bikloo.berpandroid.Classes.Owner
import com.bikloo.berpandroid.Classes.User
import com.bikloo.berpandroid.DataBase.DBUser
import com.bikloo.berpandroid.DataBase.DataStore
import kotlinx.android.synthetic.main.activity_login.*
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {
    lateinit var mSharedpreferences: SharedPreferences
    lateinit var mEditor: SharedPreferences.Editor
    lateinit var mDBUser : DBUser
    lateinit var mUsersArrayList: MutableList<User>

    override fun onCreate(savedInstanceState: Bundle?)
    {
        val dataStore = DataStore.instance.processJSON(this)
        //Testing to display if we get users from JSON File
        for(user in DataStore.instance.userList)
        {
            Log.d("User Json ------>>>",user.toString())
        }

        mDBUser = DBUser(this)
        mUsersArrayList = mDBUser.allUsers
        if(mUsersArrayList.isEmpty())
        {
            loadUserFromJSONintoDB()
        }
        else
        {
            //Test size display
            Log.d("User List size :",mUsersArrayList.size.toString())
        }
        // Testing To display Users from DB
        for(user in mDBUser.allUsers)
        {
            Log.d("User from DB----->",user.toString())
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (switchRememberMe.isChecked() == true) {
            getRememberMe()
        }

        this.supportActionBar!!.hide()
    }

    fun loadUserFromJSONintoDB()
    {
        for (user in DataStore.instance.userList) // got array list of user from JSON
        {
            mDBUser.insert(user)
        }
    }
    fun clickedLogin(view : View)
    {
        saveRememeberMe()
        if (edtEmail.text!!.toString().isEmpty() || edtEmail.text!!.toString().trim { it <= ' ' }.length == 0) {
            edtEmail.error = "Please Enter Email"
            if (edtPassword.text!!.toString().isEmpty() || edtPassword.text!!.toString().trim { it <= ' ' }.length == 0) {
                edtPassword.error = "Please Enter Password"
            }
        }
        else
        {
            if(validateEmail(edtEmail.text!!.toString()))
            {
                var exist : Boolean = false
                var existUser : User? = null
                for(user in mDBUser.allUsers)
                {
                    if(user.email.equals(edtEmail.text!!.toString()))
                    {
                        exist = true
                        existUser = user
                    }
                }
                if(exist == true)
                {
                    if(existUser!!.password.equals(edtPassword.text!!.toString()))
                    {
                        if (switchRememberMe.isChecked == true) {
                            saveRememeberMe()
                        } else {
                            saveRememeberMeEmpty()
                        }
                        Log.d("Correct Details User:",existUser.toString())


                        DataStore.selectedOwner = existUser as Owner
                        DashboardActiviy.open(this, null)
                    }
                    else
                    {
                        edtPassword.error = "Incorrect Password Entered"
                    }
                }
                else
                {
                    edtEmail.error = "No User Exist With This Email"
                }
            }
            else
            {
                edtEmail.error = "Enter Valid Email"
            }
        }

    }
    fun goToSignUp(view : View)
    {

        val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
        startActivity(intent)
        finish()
    }


    //Remember Me to save
    private fun saveRememeberMe() {
        mSharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE)
        mEditor = mSharedpreferences.edit()
        mEditor.putString("email", edtEmail.text!!.toString())
        mEditor.putString("password", edtPassword.text!!.toString())
        mEditor.commit()
    }

    //Remember Me Empty code
    private fun saveRememeberMeEmpty() {
        mSharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE)
        mEditor = mSharedpreferences.edit()
        mEditor.putString("email", "")
        mEditor.putString("password", "")
        mEditor.commit()
    }

    // to get back remember me values
    private fun getRememberMe() {
        mSharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE)
        mEditor = mSharedpreferences.edit()
        val email = mSharedpreferences.getString("email", "")
        val password = mSharedpreferences.getString("password", "")
        edtEmail.setText(email)
        edtPassword.setText(password)
    }

    //To validate email
    fun validateEmail(email: String): Boolean {

        val regex = "^[a-z0-9A-Z\\.]*@[a-z0-9A-Z]*\\.[a-zA-Z]*$"
        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(email)
        return if (matcher.matches()) {true} else {false}
    }
    fun validatePassword(password: String): Boolean? {
        val regex = "@, (?=.*[A-Z])(?=.*[0-9])(?=.*[a-z]).{8,}"
        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(password)
        return if (matcher.matches()){true} else{false}
    }
}

package com.bikloo.berpandroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bikloo.berpandroid.Classes.User
import com.bikloo.berpandroid.DataBase.DBUser
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        this.supportActionBar!!.title = "Register"
    }
    fun clickSignUp(view : View)
    {
        if(edtEmailSignup.text!!.toString().isEmpty() || edtEmailSignup.text!!.toString().trim { it <= ' ' }.length == 0)
        {
            edtEmailSignup.error = "Empty Email Field"
        }
        else
        {
            if(edtPasswordSignup.text!!.toString().isEmpty() || edtPasswordSignup.text!!.toString().trim { it <= ' ' }.length == 0)
            {
                edtPasswordSignup.error = "Empty Password Field"
            }
            else
            {
                if(edtFullNameSignup.text!!.toString().isEmpty() || edtFullNameSignup.text!!.toString().trim { it <= ' ' }.length == 0)
                {
                    edtFullNameSignup.error = "Empty Full Name Field"
                }
                else
                {
                    if(edtFullAddressSignup.text!!.toString().isEmpty() || edtFullAddressSignup.text!!.toString().trim { it <= ' ' }.length == 0)
                    {
                        edtFullAddressSignup.error = "Empty Address Field"
                    }
                    else
                    {
                        if(validateEmail(edtEmailSignup.text!!.toString()) == false)
                        {
                            edtEmailSignup.error = "Invalid Email Format"
                        }
                        else
                        {
                            if(validatePassword(edtPasswordSignup.text!!.toString()) == false)
                            {
                                edtPasswordSignup.error = "Invalid Password Format"
                            }
                            else
                            {
                                var mDBUser : DBUser = DBUser(this)
                                var mUsersArrayList: MutableList<User> = mDBUser.allUsers
                                var userID : Int = 1
                                for(user in mUsersArrayList)
                                {
                                    userID = user.userId!!
                                }
                                userID = userID+1
                                Log.d("Id : ",userID.toString())
                                mDBUser.insert(User(userID,edtEmailSignup.text!!.toString(),edtFullNameSignup.text!!.toString(),edtPasswordSignup.text!!.toString(),User.UserType.Owner,edtFullAddressSignup.text!!.toString()))

                                Toast.makeText(this,"Registration Success",Toast.LENGTH_SHORT).show()
                                mUsersArrayList = mDBUser.allUsers
                                for(user in mUsersArrayList)
                                {
                                    Log.d("Sign Up List:",user.toString())
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    //To validate email
    fun validateEmail(email: String): Boolean {

        val regex = "^[a-z0-9A-Z\\.]*@[a-z0-9A-Z]*\\.[a-zA-Z]*$"
        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(email)
        return if (matcher.matches()) {true} else {false}
    }
    fun validatePassword(password: String): Boolean {
        val regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(password)
        return if (matcher.matches()){true} else{false}
    }

    fun goToHome(view : View) {

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}

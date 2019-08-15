package com.bikloo.berpandroid

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_login.*
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {
    lateinit var mSharedpreferences: SharedPreferences
    lateinit var mEditor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (switchRememberMe.isChecked() == true) {
            getRememberMe()
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
    fun validateEmail(email: String): Boolean? {

        val regex = "^[a-z0-9A-Z\\.]*@[a-z0-9A-Z]*\\.[a-zA-Z]*$"
        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(email)
        return if (matcher.matches()) {true} else {false}
    }
}

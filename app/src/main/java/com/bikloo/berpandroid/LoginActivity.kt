package com.bikloo.berpandroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun goToSignUp(view : View) {

        val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
        startActivity(intent)
        finish()
    }
}

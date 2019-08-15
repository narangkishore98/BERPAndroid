package com.bikloo.berpandroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
    }

    fun goToHome(view : View) {

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}

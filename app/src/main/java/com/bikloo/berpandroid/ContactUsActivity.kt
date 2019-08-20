package com.bikloo.berpandroid

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.View

import kotlinx.android.synthetic.main.activity_contact_us.*
import android.net.Uri.fromParts
import android.content.Intent
import android.net.Uri
import android.R.attr.phoneNumber




class ContactUsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    fun goToMakeCall(view:View)
    {
        val phone = "+14164362548"
        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
        startActivity(intent)
    }
    fun goToMakeSms(view:View)
    {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.data = Uri.parse("smsto:+16477639826") // This ensures only SMS apps respond
        intent.putExtra("sms_body", "I want to say")
        startActivity(intent)
    }
    fun goToMakeEmail(view:View)
    {
        val emailIntent = Intent(
            Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", "canadatarlochan5268@gmail.com", null
            )
        )
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "----> Type Your Subject")
        emailIntent.putExtra(Intent.EXTRA_TEXT, "----> Type Your Message")
        startActivity(Intent.createChooser(emailIntent, "Send email..."))
    }
}

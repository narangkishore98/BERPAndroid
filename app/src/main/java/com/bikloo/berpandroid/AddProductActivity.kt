package com.bikloo.berpandroid

import android.Manifest.permission.CAMERA
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView
import java.util.jar.Manifest

class AddProductActivity : AppCompatActivity() , ZXingScannerView.ResultHandler {
    private val REQUEST_CAMERA = 1

    override fun handleResult(rawResult: Result?) {
        showAlert("Barcode Scanned " + rawResult!!.text,this)

        //mScannerView!!.resumeCameraPreview(this)
    }

    private  var mScannerView:ZXingScannerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mScannerView = ZXingScannerView(this)
        setContentView(mScannerView)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if(checkPermission())
            {
                showAlert("Permisssions Granted",this)
            }
            else
            {
                requestPermission()
            }
        }
    }

    private  fun checkPermission() : Boolean
    {
        return (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
    }

    private fun requestPermission()
    {
        ActivityCompat.requestPermissions(this, arrayOf(CAMERA),REQUEST_CAMERA)
    }
    public fun onRequestPermissionResult(requestCode:Int, permissions:Array<String>, results:Array<Int>)
    {
        when(requestCode)
        {
            REQUEST_CAMERA ->
            {
                if(results.size > 0)
                {
                    val cameraAccepted = results[0] == PackageManager.PERMISSION_GRANTED
                    if(cameraAccepted)
                    {
                        showAlert("Permission Granted",this)
                    }
                    else
                    {
                        showAlert("Permission Denied",this)
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                        {
                            if(shouldShowRequestPermissionRationale(CAMERA))
                            {

                            }
                        }
                    }
                }
            }
        }
    }

    public override fun onDestroy() {
        super.onDestroy()
        mScannerView!!.stopCamera()
    }
    public override fun onResume() {
        super.onResume()

        val currentapiVersion = android.os.Build.VERSION.SDK_INT
        if (currentapiVersion >= android.os.Build.VERSION_CODES.M) {
            if (checkPermission()) {
                if (mScannerView == null) {
                    mScannerView = ZXingScannerView(this)
                    setContentView(mScannerView)
                }
                mScannerView!!.setResultHandler(this)
                mScannerView!!.startCamera()
            } else {
                requestPermission()
            }
        }
    }

    fun showAlert(message:String, activity: Activity)
    {

        val rootView = activity.window.decorView.findViewById<View>(android.R.id.content)
        Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show()
        // val mySnackbar = Snackbar.make(findViewById(R.id.myID), "No Employees Added yet.", Snackbar.LENGTH_SHORT)
    }



    override fun onPause() {
        super.onPause()
        mScannerView!!.stopCamera()
    }

    companion object
    {
        fun open(withContext: Context, withData:Bundle?)

        {
            var i: Intent =  Intent(withContext, AddProductActivity::class.java)
            if(withData != null)
            {
                i.putExtras(withData)
            }
            withContext.startActivity(i)

        }
    }
}


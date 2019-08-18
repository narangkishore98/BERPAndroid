package com.bikloo.berpandroid

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView

class AddProductActivity : AppCompatActivity() , ZXingScannerView.ResultHandler {
    override fun handleResult(rawResult: Result?) {
        Log.v("Info", rawResult!!.getText());
        Log.v("Type", rawResult!!.getBarcodeFormat().toString());
        mScannerView!!.resumeCameraPreview(this)
    }

    private  var mScannerView:ZXingScannerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mScannerView = ZXingScannerView(this)
        setContentView(mScannerView)
    }

    override fun onResume() {
        super.onResume()
        mScannerView!!.setResultHandler(this)
        mScannerView!!.startCamera()


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


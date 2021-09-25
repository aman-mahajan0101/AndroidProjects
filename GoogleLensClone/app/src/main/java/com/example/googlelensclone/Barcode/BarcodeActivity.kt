package com.example.googlelensclone.Barcode

import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.camera.core.*
import androidx.core.content.ContextCompat
import com.example.googlelensclone.BaseLensActivity
import com.example.googlelensclone.R
import kotlinx.android.synthetic.main.activity_lens.*
import kotlinx.android.synthetic.main.activity_camera.*

class BarcodeActivity : BaseLensActivity() {


    override val imageAnalyzer = BarcodeAnalyzer()
    override fun startScanner() {
        scanBarcode()
    }

    private fun scanBarcode() {

        imageAnalysis.setAnalyzer(
            ContextCompat.getMainExecutor(this),
            imageAnalyzer
        )
    }

  }
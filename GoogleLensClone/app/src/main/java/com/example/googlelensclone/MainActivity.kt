package com.example.googlelensclone

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import com.example.googlelensclone.Barcode.BarcodeActivity
import com.example.googlelensclone.FaceDetect.FaceDetectActivity
import com.example.googlelensclone.FaceDetect.FaceDetectAnalyzer
import com.example.googlelensclone.ImageLabelAnalyzer.ImageLabelingActivity
import com.example.googlelensclone.TextDetection.TextRecognitionActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        const val PHOTO_REQ_CODE=123
        const val EXTRA_DATA="data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnTakeExtPhoto.setOnClickListener {
            val takePhoto = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePhoto, PHOTO_REQ_CODE)
        }

        btnCameraActivity.setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }

        btnBarcodeActivity.setOnClickListener {
            startActivity(Intent(this, BarcodeActivity::class.java))
        }

        btnFaceDetectActivity.setOnClickListener {
            startActivity(Intent(this, FaceDetectActivity::class.java))
        }
        btnLabelerActivity.setOnClickListener {
            startActivity(Intent(this, ImageLabelingActivity::class.java))
        }

        btnTextRecogActivity.setOnClickListener {
            startActivity(Intent(this, TextRecognitionActivity::class.java))
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode== PHOTO_REQ_CODE){
            val bitmap = data?.extras?.get(EXTRA_DATA) as Bitmap
            imgThumb.setImageBitmap(bitmap)
            return
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}
package com.alorma.intents.ui.photo

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import com.alorma.intents.R
import kotlinx.android.synthetic.main.photo_activity.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class PhotoIntentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.photo_activity)

        takePhoto.setOnClickListener {
            openCameraIntent()
        }
    }

    companion object {
        private val TAKE_PICTURE_REQUEST_CODE: Int = 1121
    }

    private lateinit var photoURI: Uri
    private lateinit var photoFile: File

    private fun openCameraIntent() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
            photoFile = createImageFile()
            photoURI = FileProvider.getUriForFile(this@PhotoIntentActivity, packageName, photoFile)
            putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
        }
        startActivityForResult(intent, TAKE_PICTURE_REQUEST_CODE)
    }

    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(imageFileName, /* prefix */".jpg", /* suffix */storageDir      /* directory */)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == TAKE_PICTURE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                image.setImageURI(photoURI)
            }
        }
    }
}
package com.alorma.intents.ui.select

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alorma.intents.R
import kotlinx.android.synthetic.main.select_activity.*

class SelectFileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.select_activity)

        selectFile.setOnClickListener {
            openSelectIntent()
        }
    }

    companion object {
        private val SELECT_FILE_REQUEST_CODE: Int = 1122
    }

    private fun openSelectIntent() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "*/*"
        }
        startActivityForResult(intent, SELECT_FILE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SELECT_FILE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                fileUri.text = data?.data?.toString()
            }
        }
    }
}
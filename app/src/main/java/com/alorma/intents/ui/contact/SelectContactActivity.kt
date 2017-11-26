package com.alorma.intents.ui.contact

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v7.app.AppCompatActivity
import com.alorma.intents.R
import kotlinx.android.synthetic.main.contact_activity.*

class SelectContactActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contact_activity)

        selectContact.setOnClickListener {
            openContactPicker()
        }
    }

    companion object {
        private val SELECT_CONTACT_REQUEST_CODE: Int = 1123
    }

    private fun openContactPicker() {
        val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
        startActivityForResult(intent, SELECT_CONTACT_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SELECT_CONTACT_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val uri = data?.data
                uri?.let {
                    contactUri.text = uri.toString()
                }
            }
        }
    }

    private fun getContactPhoto(contactUri: Uri): Uri {
        return Uri.withAppendedPath(contactUri, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY)
    }
}
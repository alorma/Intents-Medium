package com.alorma.intents

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.alorma.intents.ui.IntentLesson
import com.alorma.intents.ui.IntentsExampleAdapter
import com.alorma.intents.ui.contact.SelectContactActivity
import com.alorma.intents.ui.photo.PhotoIntentActivity
import com.alorma.intents.ui.select.SelectFileActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IntentsExampleAdapter.OnLessonCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = IntentsExampleAdapter(this)

        adapter.add(IntentLesson("Take a photo", PhotoIntentActivity::class.java))
        adapter.add(IntentLesson("Select file", SelectFileActivity::class.java))
        adapter.add(IntentLesson("Select contact", SelectContactActivity::class.java))

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onLessonsTap(intentLesson: IntentLesson) {
        intentLesson.intentClass?.let {
            startActivity(Intent(this, it))
        }
    }
}

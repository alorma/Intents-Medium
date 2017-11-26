package com.alorma.intents.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alorma.intents.R

class IntentsExampleAdapter(private val lessonCallback: OnLessonCallback) : RecyclerView.Adapter<IntentsExampleAdapter.Holder>() {

    private val items = mutableListOf<IntentLesson>()

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.populate(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.intent_row, parent, false)
        return Holder(view, lessonCallback)
    }

    fun add(intentLesson: IntentLesson) {
        items.add(intentLesson)
        notifyDataSetChanged()
    }

    class Holder(itemView: View, private val lessonCallback: OnLessonCallback) : RecyclerView.ViewHolder(itemView) {
        fun populate(intentLesson: IntentLesson) {
            itemView.findViewById<TextView>(android.R.id.text1).text = intentLesson.title

            if (intentLesson.intentClass == null) {
                itemView.isEnabled = false
                itemView.setOnClickListener {  }
            } else {
                itemView.isEnabled = true
                itemView.setOnClickListener {
                    lessonCallback.onLessonsTap(intentLesson)
                }
            }
        }
    }

    interface OnLessonCallback {
        fun onLessonsTap(intentLesson: IntentLesson)
    }
}
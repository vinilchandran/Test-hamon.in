package com.example.screeningapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.screeningapp.R
import com.example.screeningapp.model.Subject
import kotlinx.android.synthetic.main.adapter_item_subject_list.view.*

class SubjectSpinnerAdapter(context: Context, private val hotels: List<Subject>):
    ArrayAdapter<Subject>(context, R.layout.adapter_item_subject_list, hotels) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return createViewFromResource(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return createViewFromResource(position, convertView, parent)
    }

    private fun createViewFromResource(position: Int, convertView: View?, parent: ViewGroup?): View{
        val view = convertView as TextView? ?: LayoutInflater.from(context).inflate(R.layout.adapter_item_subject_list, parent, false)
        view.tv_name.text= hotels[position].name.toUpperCase()
        return view
    }
}
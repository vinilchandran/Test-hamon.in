package com.example.screeningapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.screeningapp.R
import com.example.screeningapp.model.Subject
import kotlinx.android.synthetic.main.item_subject_list.view.*
import io.reactivex.subjects.PublishSubject
import kotlin.collections.ArrayList


class SubjectAdapter() : RecyclerView.Adapter<SubjectAdapter.ViewHolder>()  {
    private val items: ArrayList<Subject> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_subject_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    fun addItems(items : ArrayList<Subject>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Subject) {
            with(itemView) {
                tv_name.text = item.name
                setOnClickListener{
                    clickSubject.onNext(item)
                }
            }
        }
    }

    private val clickSubject = PublishSubject.create<Subject>()
    fun clickEvent(): PublishSubject<Subject> {
        return clickSubject
    }
}
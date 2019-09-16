package com.example.screeningapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.screeningapp.R
import com.example.screeningapp.model.ClassRoom
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_subject_list.view.*

class ClassRoomAdapter() : RecyclerView.Adapter<ClassRoomAdapter.ViewHolder>()  {
    private val items: ArrayList<ClassRoom> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_classroom_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    fun addItems(items : ArrayList<ClassRoom>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ClassRoom) {
            with(itemView) {
                tv_name.text = item.name
                setOnClickListener{
                    clickSubject.onNext(item)
                }
            }
        }
    }

    private val clickSubject = PublishSubject.create<ClassRoom>()
    fun clickEvent(): PublishSubject<ClassRoom> {
        return clickSubject
    }
}
package com.example.screeningapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Subjects(val subjects: List<Subject>) : Parcelable{
    val subjectList: ArrayList<Subject>
        get() = ArrayList(subjects)
}

@Parcelize
data class Subject(
    val credits: Int,
    val id: Int,
    val name: String,
    val teacher: String
) : Parcelable
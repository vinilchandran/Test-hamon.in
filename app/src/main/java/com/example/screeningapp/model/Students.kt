package com.example.screeningapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Students(val students: List<Student>) : Parcelable

@Parcelize
data class Student(
    val id: Int,
    val name: String?,
    val email: String?,
    val age: Int
) : Parcelable
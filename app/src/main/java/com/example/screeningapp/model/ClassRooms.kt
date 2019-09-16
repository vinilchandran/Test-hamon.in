package com.example.screeningapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ClassRooms(val classrooms:List<ClassRoom>) : Parcelable

@Parcelize
data class ClassRoom(
    val id: Int,
    val layout: String,
    val name: String,
    val size: Int,
    val subject: String?,
    val classroom: Int?
) : Parcelable{
    val isConference: Boolean
        get() = layout == "conference"
}
package com.example.screeningapp.view

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavDirections
import com.example.screeningapp.R
import com.example.screeningapp.model.Student
import java.io.Serializable
import java.lang.UnsupportedOperationException
import kotlin.Int
import kotlin.Suppress

class StudentFragmentDirections private constructor() {
    private data class ActionOpenStudent(val student: Student) : NavDirections {
        override fun getActionId(): Int = R.id.action_openStudent

        @Suppress("CAST_NEVER_SUCCEEDS")
        override fun getArguments(): Bundle {
            val result = Bundle()
            if (Parcelable::class.java.isAssignableFrom(Student::class.java)) {
                result.putParcelable("student", this.student as Parcelable)
            } else if (Serializable::class.java.isAssignableFrom(Student::class.java)) {
                result.putSerializable("student", this.student as Serializable)
            } else {
                throw UnsupportedOperationException(Student::class.java.name +
                        " must implement Parcelable or Serializable or must be an Enum.")
            }
            return result
        }
    }

    companion object {
        fun actionOpenStudent(student: Student): NavDirections = ActionOpenStudent(student)
    }
}

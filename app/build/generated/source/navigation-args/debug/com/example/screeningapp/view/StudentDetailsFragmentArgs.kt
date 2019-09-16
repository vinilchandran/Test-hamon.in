package com.example.screeningapp.view

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavArgs
import com.example.screeningapp.model.Student
import java.io.Serializable
import java.lang.IllegalArgumentException
import java.lang.UnsupportedOperationException
import kotlin.Suppress
import kotlin.jvm.JvmStatic

data class StudentDetailsFragmentArgs(val student: Student) : NavArgs {
    @Suppress("CAST_NEVER_SUCCEEDS")
    fun toBundle(): Bundle {
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

    companion object {
        @JvmStatic
        fun fromBundle(bundle: Bundle): StudentDetailsFragmentArgs {
            bundle.setClassLoader(StudentDetailsFragmentArgs::class.java.classLoader)
            val __student : Student?
            if (bundle.containsKey("student")) {
                if (Parcelable::class.java.isAssignableFrom(Student::class.java) ||
                        Serializable::class.java.isAssignableFrom(Student::class.java)) {
                    __student = bundle.get("student") as Student?
                } else {
                    throw UnsupportedOperationException(Student::class.java.name +
                            " must implement Parcelable or Serializable or must be an Enum.")
                }
                if (__student == null) {
                    throw IllegalArgumentException("Argument \"student\" is marked as non-null but was passed a null value.")
                }
            } else {
                throw IllegalArgumentException("Required argument \"student\" is missing and does not have an android:defaultValue")
            }
            return StudentDetailsFragmentArgs(__student)
        }
    }
}

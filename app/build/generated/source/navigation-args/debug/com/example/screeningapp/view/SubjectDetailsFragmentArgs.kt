package com.example.screeningapp.view

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavArgs
import com.example.screeningapp.model.Subject
import java.io.Serializable
import java.lang.IllegalArgumentException
import java.lang.UnsupportedOperationException
import kotlin.Suppress
import kotlin.jvm.JvmStatic

data class SubjectDetailsFragmentArgs(val subject: Subject) : NavArgs {
    @Suppress("CAST_NEVER_SUCCEEDS")
    fun toBundle(): Bundle {
        val result = Bundle()
        if (Parcelable::class.java.isAssignableFrom(Subject::class.java)) {
            result.putParcelable("subject", this.subject as Parcelable)
        } else if (Serializable::class.java.isAssignableFrom(Subject::class.java)) {
            result.putSerializable("subject", this.subject as Serializable)
        } else {
            throw UnsupportedOperationException(Subject::class.java.name +
                    " must implement Parcelable or Serializable or must be an Enum.")
        }
        return result
    }

    companion object {
        @JvmStatic
        fun fromBundle(bundle: Bundle): SubjectDetailsFragmentArgs {
            bundle.setClassLoader(SubjectDetailsFragmentArgs::class.java.classLoader)
            val __subject : Subject?
            if (bundle.containsKey("subject")) {
                if (Parcelable::class.java.isAssignableFrom(Subject::class.java) ||
                        Serializable::class.java.isAssignableFrom(Subject::class.java)) {
                    __subject = bundle.get("subject") as Subject?
                } else {
                    throw UnsupportedOperationException(Subject::class.java.name +
                            " must implement Parcelable or Serializable or must be an Enum.")
                }
                if (__subject == null) {
                    throw IllegalArgumentException("Argument \"subject\" is marked as non-null but was passed a null value.")
                }
            } else {
                throw IllegalArgumentException("Required argument \"subject\" is missing and does not have an android:defaultValue")
            }
            return SubjectDetailsFragmentArgs(__subject)
        }
    }
}

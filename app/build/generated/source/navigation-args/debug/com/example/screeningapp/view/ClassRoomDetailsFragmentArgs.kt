package com.example.screeningapp.view

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavArgs
import com.example.screeningapp.model.ClassRoom
import java.io.Serializable
import java.lang.IllegalArgumentException
import java.lang.UnsupportedOperationException
import kotlin.Suppress
import kotlin.jvm.JvmStatic

data class ClassRoomDetailsFragmentArgs(val classroom: ClassRoom) : NavArgs {
    @Suppress("CAST_NEVER_SUCCEEDS")
    fun toBundle(): Bundle {
        val result = Bundle()
        if (Parcelable::class.java.isAssignableFrom(ClassRoom::class.java)) {
            result.putParcelable("classroom", this.classroom as Parcelable)
        } else if (Serializable::class.java.isAssignableFrom(ClassRoom::class.java)) {
            result.putSerializable("classroom", this.classroom as Serializable)
        } else {
            throw UnsupportedOperationException(ClassRoom::class.java.name +
                    " must implement Parcelable or Serializable or must be an Enum.")
        }
        return result
    }

    companion object {
        @JvmStatic
        fun fromBundle(bundle: Bundle): ClassRoomDetailsFragmentArgs {
            bundle.setClassLoader(ClassRoomDetailsFragmentArgs::class.java.classLoader)
            val __classroom : ClassRoom?
            if (bundle.containsKey("classroom")) {
                if (Parcelable::class.java.isAssignableFrom(ClassRoom::class.java) ||
                        Serializable::class.java.isAssignableFrom(ClassRoom::class.java)) {
                    __classroom = bundle.get("classroom") as ClassRoom?
                } else {
                    throw UnsupportedOperationException(ClassRoom::class.java.name +
                            " must implement Parcelable or Serializable or must be an Enum.")
                }
                if (__classroom == null) {
                    throw IllegalArgumentException("Argument \"classroom\" is marked as non-null but was passed a null value.")
                }
            } else {
                throw IllegalArgumentException("Required argument \"classroom\" is missing and does not have an android:defaultValue")
            }
            return ClassRoomDetailsFragmentArgs(__classroom)
        }
    }
}

package com.example.screeningapp.view

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavDirections
import com.example.screeningapp.R
import com.example.screeningapp.model.ClassRoom
import java.io.Serializable
import java.lang.UnsupportedOperationException
import kotlin.Int
import kotlin.Suppress

class ClassRoomFragmentDirections private constructor() {
    private data class ActionOpenClassRoom(val classroom: ClassRoom) : NavDirections {
        override fun getActionId(): Int = R.id.action_openClassRoom

        @Suppress("CAST_NEVER_SUCCEEDS")
        override fun getArguments(): Bundle {
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
    }

    companion object {
        fun actionOpenClassRoom(classroom: ClassRoom): NavDirections =
                ActionOpenClassRoom(classroom)
    }
}

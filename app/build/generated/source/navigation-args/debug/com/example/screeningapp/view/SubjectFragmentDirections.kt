package com.example.screeningapp.view

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavDirections
import com.example.screeningapp.R
import com.example.screeningapp.model.Subject
import java.io.Serializable
import java.lang.UnsupportedOperationException
import kotlin.Int
import kotlin.Suppress

class SubjectFragmentDirections private constructor() {
    private data class ActionOpenSubjectDetails(val subject: Subject) : NavDirections {
        override fun getActionId(): Int = R.id.action_openSubjectDetails

        @Suppress("CAST_NEVER_SUCCEEDS")
        override fun getArguments(): Bundle {
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
    }

    companion object {
        fun actionOpenSubjectDetails(subject: Subject): NavDirections =
                ActionOpenSubjectDetails(subject)
    }
}

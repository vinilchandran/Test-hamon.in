package com.example.screeningapp.view

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.screeningapp.R

class MainFragmentDirections private constructor() {
    companion object {
        fun actionOpenSubject(): NavDirections = ActionOnlyNavDirections(R.id.action_openSubject)

        fun actionOpenStudent(): NavDirections = ActionOnlyNavDirections(R.id.action_openStudent)

        fun actionOpenClassRoom(): NavDirections =
                ActionOnlyNavDirections(R.id.action_openClassRoom)
    }
}

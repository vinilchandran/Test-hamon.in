package com.example.screeningapp.model

data class Register(
    val registration: Registration
)

data class Registration(
    val id: Int,
    val student: Int,
    val subject: Int
)
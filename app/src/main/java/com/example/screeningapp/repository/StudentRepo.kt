package com.example.screeningapp.repository

import com.example.screeningapp.model.Student
import com.example.screeningapp.model.Students
import com.example.screeningapp.network.ApiInterface
import com.example.screeningapp.network.RResponse

class StudentRepo(private val apiInterface: ApiInterface) : BaseRepository() {

    suspend fun getStudentList() :  RResponse<Students>{
        return processResponse{apiInterface.studentListAsync().await()}
    }

    suspend fun getStudent(student_id : Int) :  RResponse<Student>{
        return processResponse {apiInterface.studentAsync(student_id).await()}
    }
}
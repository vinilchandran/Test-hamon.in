package com.example.screeningapp.repository

import com.example.screeningapp.model.Subject
import com.example.screeningapp.model.Subjects
import com.example.screeningapp.network.ApiInterface
import com.example.screeningapp.network.RResponse

class SubjectRepo(private val apiInterface: ApiInterface) : BaseRepository() {

    suspend fun getSubjectList() :  RResponse<Subjects>{
        return processResponse {apiInterface.subjectListAsync().await()}
    }

    suspend fun getSubject(subject_id : Int) :  RResponse<Subject>{
        return processResponse {apiInterface.subjectAsync(subject_id).await()}
    }
}
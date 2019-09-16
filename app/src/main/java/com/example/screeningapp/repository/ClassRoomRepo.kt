package com.example.screeningapp.repository

import com.example.screeningapp.model.ClassRoom
import com.example.screeningapp.model.ClassRooms
import com.example.screeningapp.model.Subjects
import com.example.screeningapp.network.ApiInterface
import com.example.screeningapp.network.RResponse

class ClassRoomRepo(private val apiInterface: ApiInterface) : BaseRepository() {

    suspend fun getClassRoomList() : RResponse<ClassRooms>{
        return processResponse{apiInterface.classRoomListAsync().await()}
    }

    suspend fun getClassRoom(classroom_id : Int) :  RResponse<ClassRoom>{
        return processResponse {apiInterface.classRoomAsync(classroom_id).await()}
    }

    suspend fun getSubjectList() : RResponse<Subjects>{
        return processResponse {apiInterface.subjectListAsync().await()}
    }

    suspend fun assignSubject(classroom_id : Int, subject_id : Int) :  RResponse<ClassRoom>{
        return processResponse {apiInterface.assignSubject2ClassAsync(classroom_id,subject_id).await()}
    }
}
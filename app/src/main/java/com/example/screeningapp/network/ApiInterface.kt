package com.example.screeningapp.network

import com.example.screeningapp.model.*
import kotlinx.coroutines.Deferred
import retrofit2.Response
import okhttp3.ResponseBody
import retrofit2.http.*
import java.util.*


interface  ApiInterface{

    @GET("subjects")
    fun subjectListAsync() : Deferred<Response<Subjects>>

    @GET("subjects/{subject_id}")
    fun subjectAsync(@Path("subject_id") subject_id : Int) : Deferred<Response<Subject>>

    @GET("students")
    fun studentListAsync() : Deferred<Response<Students>>

    @GET("students/{student_id}")
    fun studentAsync(@Path("student_id") student_id : Int) : Deferred<Response<Student>>

    @GET("classrooms")
    fun classRoomListAsync() : Deferred<Response<ClassRooms>>

    @GET("classrooms/{classroom_id}")
    fun classRoomAsync(@Path("classroom_id") classroom_id : Int) : Deferred<Response<ClassRoom>>

    @FormUrlEncoded
    @PATCH("classrooms/{classroom_id}")
    fun assignSubject2ClassAsync(@Path("classroom_id") classroom_id: Int, @Field("subject") subject:Int): Deferred<Response<ClassRoom>>
}
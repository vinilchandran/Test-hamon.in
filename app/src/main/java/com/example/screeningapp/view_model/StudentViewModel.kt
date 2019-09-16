package com.example.screeningapp.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.screeningapp.repository.StudentRepo
import com.example.screeningapp.model.Student
import com.example.screeningapp.model.Students
import com.example.screeningapp.network.ApiService
import com.example.screeningapp.network.RResponse
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class StudentViewModel: ViewModel(){
    private val parentJob = Job()
    private val coroutineContext : CoroutineContext get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)
    private val studentRepository : StudentRepo = StudentRepo(ApiService.API)

    fun getStudentList() : MutableLiveData<RResponse<Students>> {
        val studentListLiveData = MutableLiveData<RResponse<Students>>()
        scope.launch {
            val studentList = studentRepository.getStudentList()
            studentListLiveData.postValue(studentList)
        }
        return studentListLiveData
    }

    fun getStudent(student_id : Int) : MutableLiveData<RResponse<Student>>{
        val studentLiveData = MutableLiveData<RResponse<Student>>()
        scope.launch {
            val student = studentRepository.getStudent(student_id)
            studentLiveData.postValue(student)
        }
        return studentLiveData
    }

    override fun onCleared() {
        super.onCleared()
        scope.coroutineContext.cancelChildren()
    }
}
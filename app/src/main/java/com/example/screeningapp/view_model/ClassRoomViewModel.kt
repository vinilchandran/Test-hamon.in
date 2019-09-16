package com.example.screeningapp.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.screeningapp.repository.ClassRoomRepo
import com.example.screeningapp.model.ClassRoom
import com.example.screeningapp.model.ClassRooms
import com.example.screeningapp.model.Subjects
import com.example.screeningapp.network.ApiService
import com.example.screeningapp.network.RResponse
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ClassRoomViewModel : ViewModel() {
    private val parentJob = Job()
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)

    private val classRoomRepository: ClassRoomRepo = ClassRoomRepo(ApiService.API)

    fun getClassRoomList(): MutableLiveData<RResponse<ClassRooms>> {
        val classRoomsListLiveData = MutableLiveData<RResponse<ClassRooms>>()
        scope.launch {
            val classRoomList = classRoomRepository.getClassRoomList()
            classRoomsListLiveData.postValue(classRoomList)
        }
        return classRoomsListLiveData
    }

    fun getClassRoom(classroom_id: Int): MutableLiveData<RResponse<ClassRoom>> {
        val classRoomLiveData = MutableLiveData<RResponse<ClassRoom>>()
        scope.launch {
            val subject = classRoomRepository.getClassRoom(classroom_id)
            classRoomLiveData.postValue(subject)
        }
        return classRoomLiveData
    }

    fun getSubjectList(): MutableLiveData<RResponse<Subjects>> {
        val subjectListLiveData = MutableLiveData<RResponse<Subjects>>()
        scope.launch {
            val subjectList = classRoomRepository.getSubjectList()
            subjectListLiveData.postValue(subjectList)
        }
        return subjectListLiveData
    }

    fun assignSubject(classroom_id: Int, subject_id: Int): MutableLiveData<RResponse<ClassRoom>> {
        val assignSubjectLiveData = MutableLiveData<RResponse<ClassRoom>>()
        scope.launch{
            val subject = classRoomRepository.assignSubject(classroom_id, subject_id)
            assignSubjectLiveData.postValue(subject)
        }
        return assignSubjectLiveData
    }

    override fun onCleared() {
        super.onCleared()
        scope.coroutineContext.cancelChildren()
    }
}
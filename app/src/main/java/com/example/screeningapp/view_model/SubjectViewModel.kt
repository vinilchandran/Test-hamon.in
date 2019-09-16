package com.example.screeningapp.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.screeningapp.model.Subject
import com.example.screeningapp.model.Subjects
import com.example.screeningapp.network.ApiService
import com.example.screeningapp.network.RResponse
import com.example.screeningapp.repository.SubjectRepo
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SubjectViewModel: ViewModel() {
    private val parentJob = Job()
    private val coroutineContext : CoroutineContext get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)
    private val subjectRepository : SubjectRepo = SubjectRepo(ApiService.API)

    fun getSubjectList() :MutableLiveData<RResponse<Subjects>> {
        val subjectListLiveData = MutableLiveData<RResponse<Subjects>>()
        scope.launch {
            val subjectList = subjectRepository.getSubjectList()
            subjectListLiveData.postValue(subjectList)
        }
        return subjectListLiveData
    }

    fun getSubject(subject_id : Int) :MutableLiveData<RResponse<Subject>> {
        val subjectLiveData = MutableLiveData<RResponse<Subject>>()
        scope.launch {
            val subject = subjectRepository.getSubject(subject_id)
            subjectLiveData.postValue(subject)
        }
        return subjectLiveData
    }

    override fun onCleared() {
        super.onCleared()
        scope.coroutineContext.cancelChildren()
    }
}
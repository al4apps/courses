package com.al4apps.courses.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.al4apps.domain.models.Course
import com.al4apps.domain.usecases.LoadCoursesUseCase
import kotlinx.coroutines.launch

class CoursesViewModel(
    private val loadCoursesUseCase: LoadCoursesUseCase
) : ViewModel() {

    private val _loadState = MutableLiveData<LoadState>()
    val loadState: LiveData<LoadState> = _loadState

    init {
        loadCourses()
    }

    private fun loadCourses() {
        viewModelScope.launch {
            try {
                _loadState.value = LoadState.Loading
                val courses = loadCoursesUseCase()
                _loadState.value = LoadState.Success(courses)
            } catch (e: Exception) {
                e.printStackTrace()
                _loadState.value = LoadState.Error
            }
        }
    }
}

sealed class LoadState {
    data object Loading : LoadState()
    data class Success(val courses: List<Course>) : LoadState()
    data object Error : LoadState()
}
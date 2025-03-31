package com.al4apps.courses.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.al4apps.domain.usecases.CoursesDbInteractor
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val coursesDbInteractor: CoursesDbInteractor
) : ViewModel() {

    val favoriteCourses = coursesDbInteractor.getCourses()

    fun onLikeCourseClick(id: Int) {
        viewModelScope.launch {
            try {
                coursesDbInteractor.removeCourseById(id)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}
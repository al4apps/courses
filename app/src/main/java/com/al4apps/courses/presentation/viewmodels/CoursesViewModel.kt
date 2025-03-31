package com.al4apps.courses.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.al4apps.courses.utils.CustomDateFormatter
import com.al4apps.domain.models.Course
import com.al4apps.domain.usecases.CoursesDbInteractor
import com.al4apps.domain.usecases.LoadCoursesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CoursesViewModel(
    private val loadCoursesUseCase: LoadCoursesUseCase,
    private val customDateFormatter: CustomDateFormatter,
    private val coursesDbInteractor: CoursesDbInteractor
) : ViewModel() {

    private val favoriteIds = MutableStateFlow<List<Int>>(emptyList())

    private val _sortType = MutableStateFlow(SortType.DATE_ASC)

    private val _loadState = MutableStateFlow<LoadState>(LoadState.Loading)
    val loadState: StateFlow<LoadState> = _loadState.asStateFlow()

    init {
        loadCourses()
        observeFavorites()
    }

    private fun loadCourses() {
        viewModelScope.launch {
            try {
                _loadState.value = LoadState.Loading
                val courses = loadCoursesUseCase().sortByPublishDate().map { course ->
                    if (course.hasLike) {
                        coursesDbInteractor.addCoursesToDb(listOf(course))
                    }
                    course.reformatStartDate()
                }

                _loadState.value = LoadState.Success(courses)
            } catch (e: Exception) {
                e.printStackTrace()
                _loadState.value = LoadState.Error
            }
        }
    }

    private fun observeFavorites() {
        viewModelScope.launch {
            coursesDbInteractor.getCoursesIds().collect {
                favoriteIds.value = it
                updateFavorites(it)
            }
        }
    }

    fun changeSortType() {
        _sortType.value = if (_sortType.value == SortType.DATE_ASC) {
            SortType.DATE_DESC
        } else {
            SortType.DATE_ASC
        }
        val state = _loadState.value
        if (state is LoadState.Success) {
            val sortedCourses = (_loadState.value as LoadState.Success).courses.sortByPublishDate()
            _loadState.value = LoadState.Success(sortedCourses)
        }
    }

    private fun updateFavorites(ids: List<Int>) {
        val courses = getCurrentList()
        val newCourses = courses.map {
            it.copy(hasLike = ids.contains(it.id))
        }
        _loadState.value = LoadState.Success(newCourses)
    }

    fun onLikeCourseClick(id: Int) {
        val courses = getCurrentList()

        viewModelScope.launch {
            try {
                if (favoriteIds.value.contains(id)) {
                    coursesDbInteractor.removeCourseById(id)
                } else {
                    val course = courses.find { it.id == id }
                    course?.let {
                        val updatedCourse = it.copy(hasLike = true)
                        coursesDbInteractor.addCoursesToDb(listOf(updatedCourse))
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getCurrentList(): List<Course> {
        val state = _loadState.value
        return if (state is LoadState.Success) {
            state.courses
        } else emptyList()
    }

    private fun List<Course>.sortByPublishDate(): List<Course> {
        return if (_sortType.value == SortType.DATE_ASC) {
            sortedBy { it.publishDate }
        } else {
            sortedByDescending { it.publishDate }
        }
    }

    private fun Course.reformatStartDate(): Course {
        try {
            return this.copy(startDate = customDateFormatter.getFormattedDate(this.startDate))
        } catch (e: Exception) {
            e.printStackTrace()
            return this
        }
    }
}

enum class SortType {
    DATE_ASC, DATE_DESC
}

sealed class LoadState {
    data object Loading : LoadState()
    data class Success(val courses: List<Course>) : LoadState()
    data object Error : LoadState()
}
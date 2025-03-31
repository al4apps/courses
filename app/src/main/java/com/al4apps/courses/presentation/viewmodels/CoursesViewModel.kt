package com.al4apps.courses.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.al4apps.courses.utils.CustomDateFormatter
import com.al4apps.domain.models.Course
import com.al4apps.domain.usecases.LoadCoursesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CoursesViewModel(
    private val loadCoursesUseCase: LoadCoursesUseCase,
    private val customDateFormatter: CustomDateFormatter
) : ViewModel() {

    private val _sortType = MutableStateFlow(SortType.DATE_ASC)

    private val _loadState = MutableStateFlow<LoadState>(LoadState.Loading)
    val loadState: StateFlow<LoadState> = _loadState.asStateFlow()

    init {
        loadCourses()
    }

    private fun loadCourses() {
        viewModelScope.launch {
            try {
                _loadState.value = LoadState.Loading
                val courses = loadCoursesUseCase()
                    .sortByPublishDate()
                    .map { it.reformatStartDate() }

                _loadState.value = LoadState.Success(courses)
            } catch (e: Exception) {
                e.printStackTrace()
                _loadState.value = LoadState.Error
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

data class CustomDate(
    val day: Int,
    val month: Int,
    val year: Int
)
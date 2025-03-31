package com.al4apps.domain.usecases

import com.al4apps.domain.models.Course
import com.al4apps.domain.repositories.CoursesInDbRepository
import kotlinx.coroutines.flow.Flow

class CoursesDbInteractor(
    private val coursesInDbRepository: CoursesInDbRepository
) {
    fun getCourses(): Flow<List<Course>> {
        return coursesInDbRepository.observeCoursesInDb()
    }

    fun getCoursesIds(): Flow<List<Int>> {
        return coursesInDbRepository.observeCoursesIds()
    }

    suspend fun removeCourseById(id: Int) {
        coursesInDbRepository.removeCourseById(id)
    }

    suspend fun addCoursesToDb(courses: List<Course>) {
        coursesInDbRepository.addCoursesToDb(courses)
    }
}
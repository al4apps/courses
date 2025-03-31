package com.al4apps.domain.repositories

import com.al4apps.domain.models.Course
import kotlinx.coroutines.flow.Flow

interface CoursesInDbRepository {
    suspend fun addCoursesToDb(courses: List<Course>)
    fun observeCoursesInDb(): Flow<List<Course>>
    suspend fun removeCourseById(id: Int)
    fun observeCoursesIds(): Flow<List<Int>>
}
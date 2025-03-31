package com.al4apps.domain.repositories

import com.al4apps.domain.models.Course

interface CoursesRepository {
    suspend fun loadCourses(): List<Course>
}
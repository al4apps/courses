package com.al4apps.domain.usecases

import com.al4apps.domain.models.Course
import com.al4apps.domain.repositories.CoursesRepository

class LoadCoursesUseCase(
    private val coursesRepository: CoursesRepository
) {
    suspend operator fun invoke(): List<Course> {
        return coursesRepository.loadCourses()
    }
}
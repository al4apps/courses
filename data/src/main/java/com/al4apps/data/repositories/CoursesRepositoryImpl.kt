package com.al4apps.data.repositories

import com.al4apps.data.CoursesApi
import com.al4apps.domain.models.Course
import com.al4apps.domain.repositories.CoursesRepository

class CoursesRepositoryImpl(
    private val coursesApi: CoursesApi
) : CoursesRepository  {

    override suspend fun loadCourses(): List<Course> {
        try {
            val response = coursesApi.loadCourses()
            if (response.isSuccessful) {
                return response.body()?.courses?.map {
                    it.toCourse()
                } ?: emptyList()
            } else {
                throw Exception("Server response failed: ${response.code()}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return emptyList()
        }
    }

}
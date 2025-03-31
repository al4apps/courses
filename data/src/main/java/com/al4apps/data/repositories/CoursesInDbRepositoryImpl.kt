package com.al4apps.data.repositories

import com.al4apps.data.db.CourseEntity
import com.al4apps.data.db.CoursesDao
import com.al4apps.domain.models.Course
import com.al4apps.domain.repositories.CoursesInDbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CoursesInDbRepositoryImpl(
    private val coursesDao: CoursesDao
) : CoursesInDbRepository {
    override suspend fun addCoursesToDb(courses: List<Course>) {
        coursesDao.insertCourses(courses.map { it.toCourseEntity() })
    }

    override fun observeCoursesInDb(): Flow<List<Course>> {
        return coursesDao.getCourses().map { list ->
            list.map { it.toCourse() }
        }
    }

    override suspend fun removeCourseById(id: Int) {
        coursesDao.removeCourseById(id)
    }

    override fun observeCoursesIds(): Flow<List<Int>> {
        return coursesDao.getCoursesIds()
    }

    private fun Course.toCourseEntity(): CourseEntity {
        return CourseEntity(
            id = id,
            title = title,
            text = text,
            price = price,
            rate = rate,
            startDate = startDate,
            hasLike = hasLike,
            publishDate = publishDate
        )
    }
}
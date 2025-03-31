package com.al4apps.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CoursesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourses(courses: List<CourseEntity>)

    @Query("SELECT * FROM ${CourseEntity.TABLE_NAME}")
    fun getCourses(): Flow<List<CourseEntity>>

    @Query("DELETE FROM ${CourseEntity.TABLE_NAME} WHERE ${CourseEntity.COLUMN_ID} = :id")
    suspend fun removeCourseById(id: Int)

    @Query("SELECT ${CourseEntity.COLUMN_ID} FROM ${CourseEntity.TABLE_NAME}")
    fun getCoursesIds(): Flow<List<Int>>
}
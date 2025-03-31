package com.al4apps.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CourseEntity::class],
    version = AppDatabase.DB_VERSION,
    exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun coursesDao(): CoursesDao

    companion object {
        const val DB_NAME = "courses_db"
        const val DB_VERSION = 1
    }
}
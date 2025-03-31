package com.al4apps.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.al4apps.domain.models.Course

@Entity(tableName = CourseEntity.TABLE_NAME)
data class CourseEntity(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    val id: Int,

    @ColumnInfo(name = COLUMN_TITLE)
    val title: String,

    @ColumnInfo(name = COLUMN_TEXT)
    val text: String,

    @ColumnInfo(name = COLUMN_PRICE)
    val price: String,

    @ColumnInfo(name = COLUMN_RATE)
    val rate: String,

    @ColumnInfo(name = COLUMN_START_DATE)
    val startDate: String,

    @ColumnInfo(name = COLUMN_HAS_LIKE)
    val hasLike: Boolean,

    @ColumnInfo(name = COLUMN_PUBLISH_DATE)
    val publishDate: String
) {
    fun toCourse(): Course {
        return Course(
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

    companion object {
        const val TABLE_NAME = "courses"

        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_TEXT = "text"
        const val COLUMN_PRICE = "price"
        const val COLUMN_RATE = "rate"
        const val COLUMN_START_DATE = "start_date"
        const val COLUMN_HAS_LIKE = "has_like"
        const val COLUMN_PUBLISH_DATE = "publish_date"
    }
}
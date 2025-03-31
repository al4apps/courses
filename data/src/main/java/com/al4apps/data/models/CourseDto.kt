package com.al4apps.data.models

import com.al4apps.domain.models.Course
import com.google.gson.annotations.SerializedName

/**
 * Т.к. в документации нет данных о nullabilities, все поля сделаны nullable для безопасности.
 * Но чтобы не усложнять код все null поля при маппинге заменяются на значения-заглушки
 */

class CoursesResponse(
    @SerializedName("courses")
    val courses: List<CourseDto>
)

data class CourseDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("text")
    val text: String?,
    @SerializedName("price")
    val price: String?,
    @SerializedName("rate")
    val rate: String?,
    @SerializedName("startDate")
    val startDate: String?,
    @SerializedName("hasLike")
    val hasLike: Boolean?,
    @SerializedName("publishDate")
    val publishDate: String?
) {
    fun toCourse(): Course {
        return Course(
            id = id ?: 0,
            title = title ?: "Неизвестный курс",
            text = text ?: "Нет данных",
            price = price ?: "??",
            rate = rate ?: "??",
            startDate = startDate ?: "??",
            hasLike = hasLike ?: false,
            publishDate = publishDate ?: "??"
        )
    }
}
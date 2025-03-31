package com.al4apps.data

import com.al4apps.data.models.CoursesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CoursesApi {

    @GET("u/0/uc")
    suspend fun loadCourses(
        @Query("id") id: String = "15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q",
        @Query("export") export: String = "download",
    ): Response<CoursesResponse>
}
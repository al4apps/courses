package com.al4apps.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.al4apps.data.CoursesApi
import com.al4apps.data.db.AppDatabase
import com.al4apps.data.db.CoursesDao
import com.al4apps.data.repositories.CoursesInDbRepositoryImpl
import com.al4apps.data.repositories.CoursesRepositoryImpl
import com.al4apps.data.repositories.LaunchStateRepositoryImpl
import com.al4apps.domain.repositories.CoursesInDbRepository
import com.al4apps.domain.repositories.CoursesRepository
import com.al4apps.domain.repositories.LaunchStateRepository
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

const val PREFS_DATA_STORE = "PREFS_DATA_STORE"
val Context.dataStore by preferencesDataStore(name = PREFS_DATA_STORE)

val dataModule = module {
    single<DataStore<Preferences>> { get<Context>().dataStore }
    single<LaunchStateRepository> { LaunchStateRepositoryImpl(dataStore = get()) }
    single<CoursesRepository> { CoursesRepositoryImpl(coursesApi = get()) }
    single<CoursesInDbRepository> { CoursesInDbRepositoryImpl(coursesDao = get()) }

    single<CoursesApi> {
        val baseUrl = "https://drive.usercontent.google.com/"

        val okHttpClient = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()

        retrofit.create<CoursesApi>()
    }

    single<AppDatabase> {
        Room.databaseBuilder(
            get<Context>(),
            AppDatabase::class.java,
            AppDatabase.DB_NAME
        ).build()
    }

    single<CoursesDao> { get<AppDatabase>().coursesDao() }
}

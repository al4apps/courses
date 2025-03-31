package com.al4apps.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.al4apps.data.repositories.LaunchStateRepositoryImpl
import com.al4apps.domain.repositories.LaunchStateRepository
import org.koin.dsl.module

const val PREFS_DATA_STORE = "PREFS_DATA_STORE"
val Context.dataStore by preferencesDataStore(name = PREFS_DATA_STORE)

val dataModule = module {
    single<DataStore<Preferences>> { get<Context>().dataStore }
    single <LaunchStateRepository> { LaunchStateRepositoryImpl(dataStore = get()) }
}

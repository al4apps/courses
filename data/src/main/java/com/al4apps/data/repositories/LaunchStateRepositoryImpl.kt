package com.al4apps.data.repositories

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.al4apps.domain.models.LaunchState
import com.al4apps.domain.repositories.LaunchStateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LaunchStateRepositoryImpl(
    private val dataStore: DataStore<Preferences>
): LaunchStateRepository {


    override fun getLaunchState(): Flow<LaunchState> {
        return dataStore.data.map {
            val stateNumber = it[LAUNCH_STATE_PREFS_KEY]
            LaunchState.fromPrefNumber(stateNumber)
        }
    }

    override suspend fun setLaunchState(launchState: LaunchState) {
        dataStore.edit { preferences ->
            preferences[LAUNCH_STATE_PREFS_KEY] = launchState.toPrefNumber()
        }
    }

    companion object {
        private const val PREFS_LAUNCH_STATE = "PREFS_LAUNCH_STATE"
        private val LAUNCH_STATE_PREFS_KEY = intPreferencesKey(PREFS_LAUNCH_STATE)
    }
}
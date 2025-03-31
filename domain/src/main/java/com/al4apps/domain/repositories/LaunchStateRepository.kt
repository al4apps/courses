package com.al4apps.domain.repositories

import com.al4apps.domain.models.LaunchState
import kotlinx.coroutines.flow.Flow

interface LaunchStateRepository {
    fun getLaunchState(): Flow<LaunchState>
    suspend fun setLaunchState(launchState: LaunchState)
}
package com.al4apps.domain.usecases

import com.al4apps.domain.models.LaunchState
import com.al4apps.domain.repositories.LaunchStateRepository
import kotlinx.coroutines.flow.Flow

class LaunchStateUseCase(
    private val launchStateRepository: LaunchStateRepository,
) {
    fun getLaunchState(): Flow<LaunchState> {
        return launchStateRepository.getLaunchState()
    }
    suspend fun setLaunchState(launchState: LaunchState) {
        launchStateRepository.setLaunchState(launchState)
    }
}
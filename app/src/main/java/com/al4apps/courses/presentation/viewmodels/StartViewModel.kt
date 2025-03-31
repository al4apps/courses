package com.al4apps.courses.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.al4apps.domain.models.LaunchState
import com.al4apps.domain.usecases.LaunchStateUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class StartViewModel(
    private val launchStateUseCase: LaunchStateUseCase,
) : ViewModel() {

    val launchState = launchStateUseCase.getLaunchState()
        .catch {
            it.printStackTrace()
            this.emit(LaunchState.FIRST_START)
        }

    fun onOnboardingShowed() {
        setLaunchState(LaunchState.UNAUTHORIZED)
    }

    fun onAuthorized() {
        setLaunchState(LaunchState.AUTHORIZED)
    }

    private fun setLaunchState(launchState: LaunchState) {
        viewModelScope.launch {
            try {
                launchStateUseCase.setLaunchState(launchState)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
package com.al4apps.courses.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.al4apps.domain.models.LaunchState
import com.al4apps.domain.usecases.LaunchStateUseCase

class StartViewModel(
    private val launchStateUseCase: LaunchStateUseCase,
) : ViewModel() {

    private val _launchState = MutableLiveData<LaunchState>()
    val launchState: LiveData<LaunchState> get() = _launchState

    init {
        _launchState.value = launchStateUseCase.getLaunchState()
    }


    fun setLaunchState(launchState: LaunchState) {
        launchStateUseCase.setLaunchState(launchState)
    }
}
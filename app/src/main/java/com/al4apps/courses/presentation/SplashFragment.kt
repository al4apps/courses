package com.al4apps.courses.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.al4apps.courses.R
import com.al4apps.domain.models.LaunchState

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val state = listOf(
            LaunchState.UNAUTHORIZED,
            LaunchState.AUTHORIZED,
            LaunchState.FIRST_START
        ).random()
        navigateOnAppStart(state)
        return null
    }

    private fun navigateOnAppStart(launchState: LaunchState) {
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.splashFragment, true, saveState = false)
            .build()

        val destinationId = when (launchState) {
            LaunchState.FIRST_START -> R.id.onboardingFragment
            LaunchState.UNAUTHORIZED -> R.id.loginFragment
            LaunchState.AUTHORIZED -> R.id.mainFragment
        }
        findNavController().navigate(resId = destinationId, args = null, navOptions = navOptions)
    }
}
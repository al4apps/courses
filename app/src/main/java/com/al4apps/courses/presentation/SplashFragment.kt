package com.al4apps.courses.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.al4apps.courses.R
import com.al4apps.courses.presentation.viewmodels.StartViewModel
import com.al4apps.domain.models.LaunchState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class SplashFragment : Fragment() {

    private val startViewModel: StartViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLaunchState()
    }

    private fun observeLaunchState() {
        viewLifecycleOwner.lifecycleScope.launch {
            startViewModel.launchState.collect {
                navigateOnAppStart(it)
            }
        }
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
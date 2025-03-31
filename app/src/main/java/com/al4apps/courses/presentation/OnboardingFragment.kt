package com.al4apps.courses.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.al4apps.courses.R
import com.al4apps.courses.databinding.FragmentOnboardingBinding
import com.al4apps.courses.presentation.viewmodels.StartViewModel
import com.al4apps.courses.utils.AbstractFragment
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class OnboardingFragment :
    AbstractFragment<FragmentOnboardingBinding>(FragmentOnboardingBinding::inflate) {

    private val startViewModel: StartViewModel by activityViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startViewModel.onOnboardingShowed()
        binding.continueButton.setOnClickListener { navigateToLoginScreen() }
    }

    private fun navigateToLoginScreen() {
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.onboardingFragment, true, saveState = false)
            .setLaunchSingleTop(true)
            .build()
        findNavController().navigate(
            resId = R.id.loginFragment,
            args = null,
            navOptions = navOptions
        )
    }

}
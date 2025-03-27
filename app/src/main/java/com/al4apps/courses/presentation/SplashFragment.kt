package com.al4apps.courses.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.al4apps.courses.R

class SplashFragment: Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (false) {
            findNavController().navigate(R.id.action_splashFragment_to_onboardingFragment)
        } else {
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}
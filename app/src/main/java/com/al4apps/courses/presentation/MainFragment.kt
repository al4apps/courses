package com.al4apps.courses.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.al4apps.courses.R
import com.al4apps.courses.databinding.FragmentMainBinding
import com.al4apps.courses.utils.AbstractFragment

class MainFragment : AbstractFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavController()
    }

    private fun setupNavController() {
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.mainContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        binding.mainBottomNavigation.setupWithNavController(navController)
    }
}
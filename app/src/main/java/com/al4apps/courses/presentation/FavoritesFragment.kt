package com.al4apps.courses.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.al4apps.courses.R
import com.al4apps.courses.databinding.FragmentFavoritesBinding
import com.al4apps.courses.presentation.adapter.CoursesAdapter
import com.al4apps.courses.presentation.viewmodels.FavoritesViewModel
import com.al4apps.courses.utils.AbstractFragment
import com.al4apps.courses.utils.autoCleared
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment :
    AbstractFragment<FragmentFavoritesBinding>(FragmentFavoritesBinding::inflate) {

    private val viewModel: FavoritesViewModel by viewModel()
    private var coursesAdapter: CoursesAdapter by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObservers()
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.favoriteCourses.collectLatest {
                    coursesAdapter.submitList(it)
                }
            }
        }
    }

    private fun initRecyclerView() {
        coursesAdapter = CoursesAdapter(
            onClick = {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.toast_course_id_text, it),
                    Toast.LENGTH_SHORT
                ).show()
            },
            onMarkClick = {
                viewModel.onLikeCourseClick(it)
            }
        )
        binding.coursesRecyclerView.adapter = coursesAdapter
    }
}
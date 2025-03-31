package com.al4apps.courses.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.al4apps.courses.R
import com.al4apps.courses.databinding.FragmentCoursesBinding
import com.al4apps.courses.presentation.adapter.CoursesAdapter
import com.al4apps.courses.presentation.viewmodels.CoursesViewModel
import com.al4apps.courses.presentation.viewmodels.LoadState
import com.al4apps.courses.utils.AbstractFragment
import com.al4apps.courses.utils.autoCleared
import org.koin.androidx.viewmodel.ext.android.viewModel

class CoursesFragment : AbstractFragment<FragmentCoursesBinding>(FragmentCoursesBinding::inflate) {

    private var coursesAdapter: CoursesAdapter by autoCleared()
    private val viewModel: CoursesViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        viewModel.loadState.observe(viewLifecycleOwner) {
            onLoadStateChanged(it)
        }
    }

    private fun onLoadStateChanged(loadState: LoadState) {
        when (loadState) {
            is LoadState.Loading -> {
                binding.progressCircular.visibility = View.VISIBLE
            }

            is LoadState.Success -> {
                binding.progressCircular.visibility = View.GONE
                coursesAdapter.submitList(loadState.courses)
            }

            is LoadState.Error -> {
                binding.progressCircular.visibility = View.GONE
                Toast.makeText(requireContext(), R.string.toast_load_error_text, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun initAdapter() {
        coursesAdapter = CoursesAdapter {
            Toast.makeText(
                requireContext(),
                getString(R.string.toast_course_id_text, it),
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.coursesRecyclerView.adapter = coursesAdapter
    }
}
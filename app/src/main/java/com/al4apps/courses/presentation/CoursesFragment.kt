package com.al4apps.courses.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.al4apps.courses.databinding.FragmentCoursesBinding
import com.al4apps.courses.presentation.adapter.CoursesAdapter
import com.al4apps.courses.utils.AbstractFragment
import com.al4apps.courses.utils.autoCleared
import com.al4apps.domain.models.Course

class CoursesFragment : AbstractFragment<FragmentCoursesBinding>(FragmentCoursesBinding::inflate) {

    private var coursesAdapter: CoursesAdapter by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        coursesAdapter.submitList(generateCourses())
    }

    private fun initAdapter() {
        coursesAdapter = CoursesAdapter {
            Toast.makeText(requireContext(), "id курса: $it", Toast.LENGTH_SHORT).show()
        }
        binding.coursesRecyclerView.adapter = coursesAdapter
    }

    private fun generateCourses(): List<Course> {
        return listOf(
            Course(
                id = 100,
                title = "Java-разработчик с нуля",
                text = "Освойте backend-разработку и программирование на Java, фреймворки Spring и Maven, работу с базами данных и API. Создайте свой собственный проект, собрав портфолио и став востребованным специалистом для любой IT компании.",
                price = "999",
                rate = "4.9",
                startDate = "2024-05-22",
                hasLike = false,
                publishDate = "2024-02-02"
            ), Course(
                id = 101,
                title = "3D-дженералист",
                text = "Освой профессию 3D-дженералиста и стань универсальным специалистом, который умеет создавать 3D-модели, текстуры и анимации, а также",
                price = "12 000",
                rate = "3.9",
                startDate = "2024-09-10",
                hasLike = false,
                publishDate = "2024-01-20"
            ), Course(
                id = 102,
                title = "Python Advanced. Для продвинутых",
                text = "Вы узнаете, как разрабатывать гибкие и высокопроизводительные серверные приложения на",
                price = "1 299",
                rate = "4.3",
                startDate = "2024-10-12",
                hasLike = true,
                publishDate = "2024-08-10"
            ), Course(
                id = 103,
                title = "Системный аналитик",
                text = "Освоите навыки системной аналитики с нуля за 9 месяцев. Будет очень много практики на реальных проектах, чтобы вы могли сразу старто",
                price = "1 199",
                rate = "4.5",
                startDate = "2024-04-15",
                hasLike = false,
                publishDate = "2024-01-13"
            ), Course(
                id = 104,
                title = "Аналитик данных",
                text = "В этом уроке вы узнаете, кто такой аналитик данных и какие задачи он решает. А главное — мы расска",
                price = "899",
                rate = "4.7",
                startDate = "2024-06-20",
                hasLike = false,
                publishDate = "2024-03-12"
            )

        )
    }
}
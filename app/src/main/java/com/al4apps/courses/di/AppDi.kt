package com.al4apps.courses.di

import com.al4apps.courses.presentation.viewmodels.CoursesViewModel
import com.al4apps.courses.presentation.viewmodels.FavoritesViewModel
import com.al4apps.courses.presentation.viewmodels.StartViewModel
import com.al4apps.courses.utils.CustomDateFormatter
import com.al4apps.domain.usecases.CoursesDbInteractor
import com.al4apps.domain.usecases.LaunchStateUseCase
import com.al4apps.domain.usecases.LoadCoursesUseCase
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory<LaunchStateUseCase> { LaunchStateUseCase(launchStateRepository = get()) }
    viewModel<StartViewModel> { StartViewModel(launchStateUseCase = get()) }

    factory<CoursesDbInteractor> { CoursesDbInteractor(coursesInDbRepository = get()) }
    factory<LoadCoursesUseCase> { LoadCoursesUseCase(coursesRepository = get()) }
    viewModel<CoursesViewModel> {
        CoursesViewModel(
            loadCoursesUseCase = get(),
            customDateFormatter = get(),
            coursesDbInteractor = get()
        )
    }

    factory<CustomDateFormatter> {
        CustomDateFormatter(context = get())
    }

    viewModel<FavoritesViewModel> {
        FavoritesViewModel(coursesDbInteractor = get())
    }
}
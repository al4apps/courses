package com.al4apps.courses.di

import com.al4apps.courses.presentation.viewmodels.CoursesViewModel
import com.al4apps.courses.presentation.viewmodels.StartViewModel
import com.al4apps.domain.usecases.LaunchStateUseCase
import com.al4apps.domain.usecases.LoadCoursesUseCase
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory<LaunchStateUseCase> { LaunchStateUseCase(launchStateRepository = get()) }
    viewModel<StartViewModel> { StartViewModel(launchStateUseCase = get()) }

    factory<LoadCoursesUseCase> { LoadCoursesUseCase(coursesRepository = get()) }
    viewModel<CoursesViewModel> { CoursesViewModel(loadCoursesUseCase = get()) }
}
package com.nepplus.practiceproject.modules

import com.google.gson.GsonBuilder
import com.nepplus.practiceproject.network.Client
import com.nepplus.practiceproject.remotedatasource.GithubRepository
import com.nepplus.practiceproject.remotedatasource.GithubRepositoryImpl
import com.nepplus.practiceproject.viewmodel.GithubViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val utilModule = module {
    single { GsonBuilder().setPrettyPrinting().create()}}

val repositoryModule = module {
    single { Client(get()) }
    single <GithubRepository> { GithubRepositoryImpl(get())}
}

val viewModelModule = module {
    viewModel { GithubViewModel(get()) }
}

val applicationModule = listOf(
   utilModule, repositoryModule, viewModelModule
)




package com.example.geoknow.DI

import com.example.geoknow.Model.Logic.CountriesService
import com.example.geoknow.ViewModel.CountriesViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: CountriesService)

    fun inject(viewModel: CountriesViewModel)
}
package com.example.geoknow.Model.Logic

import com.example.geoknow.DI.DaggerApiComponent
import com.example.geoknow.Model.Data.Country
import com.example.geoknow.Model.Logic.CountriesApi
import io.reactivex.Single
import javax.inject.Inject

class CountriesService {

    @Inject
    lateinit var api: CountriesApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getCountries(): Single<List<Country>> {
        return api.getCountries()
    }
}
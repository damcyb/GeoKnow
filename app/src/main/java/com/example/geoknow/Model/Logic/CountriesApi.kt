package com.example.geoknow.Model.Logic

import com.example.geoknow.Model.Data.Country
import io.reactivex.Single
import retrofit2.http.GET

interface CountriesApi {

    @GET("DevTides/countries/master/countriesV2.json")
    fun getCountries(): Single<List<Country>>
}
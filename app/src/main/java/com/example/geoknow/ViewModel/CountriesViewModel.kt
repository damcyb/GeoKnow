package com.example.geoknow.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geoknow.Model.Country

class CountriesViewModel: ViewModel() {

    val countryList = MutableLiveData<List<Country>>()
    val loading = MutableLiveData<Boolean>()
    val countryLoadError = MutableLiveData<Boolean>()

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        val mockData = listOf<Country>(
            Country("Country A"),
            Country("Country A"),
            Country("Country A"),
            Country("Country A"),
            Country("Country A"),
            Country("Country A"),
            Country("Country A"),
            Country("Country A")
        )

        countryList.value = mockData
        countryLoadError.value = false
        loading.value = false
    }
}
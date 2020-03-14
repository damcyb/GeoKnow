package com.example.geoknow.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geoknow.DI.DaggerApiComponent
import com.example.geoknow.Model.Logic.CountriesService
import com.example.geoknow.Model.Data.Country
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CountriesViewModel: ViewModel() {

    //private val countriesService = CountriesService()
    @Inject
    lateinit var countriesService: CountriesService
    private val disposable = CompositeDisposable()

    init {
        DaggerApiComponent.create().inject(this)
    }

    val countryList = MutableLiveData<List<Country>>()
    val loading = MutableLiveData<Boolean>()
    val countryLoadError = MutableLiveData<Boolean>()

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        loading.value = true
        disposable.add(
            countriesService.getCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>() {
                    override fun onSuccess(countriesData: List<Country>) {
                        countryList.value = countriesData
                        loading.value = false
                        countryLoadError.value = false
                    }

                    override fun onError(e: Throwable) {
                        loading.value = false
                        countryLoadError.value = true
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
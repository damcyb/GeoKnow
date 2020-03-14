package com.example.geoknow.View.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geoknow.R
import com.example.geoknow.View.Adapter.CountriesAdapter
import com.example.geoknow.ViewModel.CountriesViewModel
import kotlinx.android.synthetic.main.activity_countries.*

class CountriesActivity : AppCompatActivity() {

    private val viewModel: CountriesViewModel by lazy {
        ViewModelProviders.of(this).get(CountriesViewModel::class.java)
    }

    private val countriesAdapter = CountriesAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countries)

        viewModel.refresh()

        countriesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.countryList.observe(this, Observer { countryList ->
            countryList.let {
                countriesRecyclerView.visibility = View.VISIBLE
                countriesAdapter.updateCountries(it)
            }
        })
        viewModel.loading.observe(this, Observer { loading ->
            loading.let {
                if(it) {
                    loadingProgressBar.visibility = View.VISIBLE
                    errorMessageTxt.visibility = View.GONE
                    countriesRecyclerView.visibility = View.GONE
                } else {
                    loadingProgressBar.visibility = View.GONE
                }
            }
        })
        viewModel.countryLoadError.observe(this, Observer { error ->
            error.let {
                if(it) {
                    errorMessageTxt.visibility = View.VISIBLE
                } else {
                    errorMessageTxt.visibility = View.GONE
                }
            }
        })
    }
}

package com.thomas.walmarttest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import com.thomas.walmarttest.R
import com.thomas.walmarttest.common.isNetworkAvailable
import com.thomas.walmarttest.databinding.ActivityMainBinding
import com.thomas.walmarttest.model.Country
import com.thomas.walmarttest.model.Repository
import com.thomas.walmarttest.model.Resource
import com.thomas.walmarttest.model.remote.ApiService
import com.thomas.walmarttest.view.adapters.CountryAdapter
import com.thomas.walmarttest.viewmodel.MainVMFactory
import com.thomas.walmarttest.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val viewModel: MainViewModel by viewModels {
        MainVMFactory(Repository(ApiService.getInstance()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupObserver()
        setupEvents()
        loadCountries()
    }

    private fun setupEvents() {
        binding.btnRetry.setOnClickListener {
            loadCountries()
        }
    }

    fun loadCountries() {
        if (!isNetworkAvailable(this)) {
            binding.tvMsg.text = getString(R.string.error_no_internet)
            binding.retryGroup.visibility = VISIBLE
            binding.rvCountries.visibility = GONE
            binding.loadingGroup.visibility = GONE
            return
        }

        viewModel.loadCountries()
    }

    fun setupObserver() {
        viewModel.countryList.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    with(binding) {
                        retryGroup.visibility = GONE
                        loadingGroup.visibility = VISIBLE
                        rvCountries.visibility = GONE
                    }
                }
                is Resource.Message -> {
                    with(binding) {

                        retryGroup.visibility = VISIBLE
                        loadingGroup.visibility = GONE
                        rvCountries.visibility = GONE
                        tvMsg.text = it.msg
                    }
                }
                is Resource.Success -> {
                    with(binding) {
                        retryGroup.visibility = GONE
                        loadingGroup.visibility = GONE
                        rvCountries.visibility = VISIBLE
                        rvCountries.adapter = CountryAdapter(it.data)
                    }
                }
            }
        }
    }


}
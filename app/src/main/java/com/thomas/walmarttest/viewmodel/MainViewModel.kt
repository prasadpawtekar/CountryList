package com.thomas.walmarttest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thomas.walmarttest.common.getInfo
import com.thomas.walmarttest.model.Country
import com.thomas.walmarttest.model.Repository
import com.thomas.walmarttest.model.Resource
import com.thomas.walmarttest.model.Resource.Loading.isLoading
import com.thomas.walmarttest.model.Resource.Loading.succeeded
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainViewModel(val repository: Repository): ViewModel() {
    private val _countryList = MutableLiveData<Resource<List<Country>>>()
    val countryList: LiveData<Resource<List<Country>>> = _countryList

    fun loadCountries() {
        if(_countryList.value?.isLoading == true) {
            return
        }
        if(_countryList.value?.succeeded == true) {
            return
        }
        viewModelScope.launch(IO) {
            try {
                _countryList.postValue(Resource.Loading)
                val response = repository.getCountryList()

                if(!response.isSuccessful) {
                    _countryList.postValue(Resource.Message(response.errorBody()?.string() ?: "Unknown error. Please retry."))
                    return@launch
                }

                val countries = response.body()

                if(countries == null) {
                    _countryList.postValue(Resource.Message("Empty response from server. Please retry."))
                    return@launch
                }

                _countryList.postValue(Resource.Success(countries))
            } catch (e: Exception) {
                _countryList.postValue(Resource.Message("Error: ${e.getInfo()}"))
            }
        }
    }
}
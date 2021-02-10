package com.decwujot.countires.framework.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decwujot.countires.core.data.model.Country
import com.decwujot.countires.framework.UseCases
import kotlinx.coroutines.launch
import retrofit2.Response


open class CountriesViewModel(private val useCases: UseCases) : ViewModel() {

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>>
        get() = _countries

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    init {
        fetchCountries()
    }

    open fun fetchCountries() {
        _isLoading.value = true
        viewModelScope.launch {
            val response = useCases.fetchAllCountries()
            if (response.isSuccessful) {
                _countries.value = response.body()
                _isLoading.value = false
            } else {
                _errorMessage.value = response.errorBody().toString()
                _isLoading.value = false
            }
        }
    }
}
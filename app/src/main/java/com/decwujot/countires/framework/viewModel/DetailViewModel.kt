package com.decwujot.countires.framework.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decwujot.countires.core.data.model.Country
import com.decwujot.countires.framework.UseCases
import kotlinx.coroutines.launch

class DetailViewModel(private val useCases: UseCases) : ViewModel() {

    private val _country = MutableLiveData<Country>()
    val country: LiveData<Country>
        get() = _country

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    fun fetchCountry(alphaCode: String) {
        viewModelScope.launch {
            val response = useCases.fetchCountryByAlphaCode(alphaCode)
            if (response.isSuccessful) {
                _country.value = response.body()
                _isLoading.value = false
            } else {
                _errorMessage.value = response.errorBody().toString()
                _isLoading.value = false
            }
        }
    }
}
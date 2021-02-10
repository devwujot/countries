package com.decwujot.countires.framework.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.decwujot.countires.framework.utils.SingleLiveEvent


class SplashScreenViewModel : ViewModel() {

    private val _isGoing = SingleLiveEvent<Boolean>()
    val isGoing: LiveData<Boolean>
        get() = _isGoing

    fun goToCountriesFragment() {
        _isGoing.value = true
    }
}
package com.decwujot.countires.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.decwujot.countires.R
import com.decwujot.countires.core.data.model.Country
import com.decwujot.countires.databinding.FragmentDetailBinding
import com.decwujot.countires.databinding.FragmentSplashScreenBinding
import com.decwujot.countires.framework.utils.reObserve
import com.decwujot.countires.framework.utils.showToast
import com.decwujot.countires.framework.viewModel.DetailViewModel
import com.decwujot.countires.framework.viewModel.SplashScreenViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailViewModel by viewModel()
    private val args: DetailFragmentArgs by navArgs()

    private val countryObserver = Observer<Country> { country ->
        if (country != null) {
            binding.country = country
        }
    }

    private val isLoadingObserver = Observer<Boolean> { isLoading ->
        if (isLoading) {
            binding.progressbarDetail.visibility = View.VISIBLE
        } else {
            binding.progressbarDetail.visibility = View.GONE
        }
    }

    private val errorMessageObserver = Observer<String> { errorMessage ->
        if (errorMessage.isNotBlank()) {
            context?.showToast(errorMessage)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchCountry()

        with(viewModel) {
            country.reObserve(this@DetailFragment, countryObserver)
            isLoading.reObserve(this@DetailFragment, isLoadingObserver)
            errorMessage.reObserve(this@DetailFragment, errorMessageObserver)
        }
    }

    private fun fetchCountry() {
        val alphaCode = args.alphaCode
        viewModel.fetchCountry(alphaCode)
    }
}
package com.decwujot.countires.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.decwujot.countires.R
import com.decwujot.countires.core.data.model.Country
import com.decwujot.countires.databinding.FragmentCountriesBinding
import com.decwujot.countires.databinding.FragmentSplashScreenBinding
import com.decwujot.countires.framework.utils.reObserve
import com.decwujot.countires.framework.utils.showToast
import com.decwujot.countires.framework.viewModel.CountriesViewModel
import com.decwujot.countires.framework.viewModel.SplashScreenViewModel
import com.decwujot.countires.presentation.adapters.CountriesListAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class CountriesFragment : Fragment() {

    private lateinit var binding: FragmentCountriesBinding
    private lateinit var countriesListAdapter: CountriesListAdapter
    private val viewModel: CountriesViewModel by viewModel()

    private val countriesObserver = Observer<List<Country>> { list ->
        if (!list.isNullOrEmpty()) {
            countriesListAdapter.updateCountriesList(list)
        }
    }

    private val isLoadingObserver = Observer<Boolean> { isLoading ->
        if (isLoading) {
            binding.countriesProgress.visibility = View.VISIBLE
            binding.countriesList.visibility = View.GONE
        } else {
            binding.countriesProgress.visibility = View.GONE
            binding.countriesList.visibility = View.VISIBLE
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
        binding = FragmentCountriesBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCountriesListAdapter()

        with(viewModel) {
            countries.reObserve(this@CountriesFragment, countriesObserver)
            isLoading.reObserve(this@CountriesFragment, isLoadingObserver)
            errorMessage.reObserve(this@CountriesFragment, errorMessageObserver)
        }
    }

    private fun initCountriesListAdapter() {
        countriesListAdapter = CountriesListAdapter(arrayListOf())
        countriesListAdapter.onCountryClickListener = { alphaCode ->
            val action =
                CountriesFragmentDirections.actionCountriesFragmentToDetailFragment(alphaCode)
            findNavController().navigate(action)
        }
        binding.countriesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesListAdapter
        }
    }
}
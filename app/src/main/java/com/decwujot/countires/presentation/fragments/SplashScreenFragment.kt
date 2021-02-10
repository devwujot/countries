package com.decwujot.countires.presentation.fragments

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.HandlerCompat.postDelayed
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.decwujot.countires.databinding.FragmentSplashScreenBinding
import com.decwujot.countires.framework.utils.reObserve
import com.decwujot.countires.framework.viewModel.SplashScreenViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SplashScreenFragment : Fragment() {

    private lateinit var binding: FragmentSplashScreenBinding
    private val viewModel: SplashScreenViewModel by viewModel()

    private var isGoingObserver = Observer<Boolean> { isGoing ->
        if (isGoing) {
            navigate()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSplashScreen()

        with(viewModel) {
            isGoing.reObserve(this@SplashScreenFragment, isGoingObserver)
        }
    }

    private fun initSplashScreen() {
        Handler().postDelayed({
            viewModel.goToCountriesFragment()
        }, 3000L)
    }

    private fun navigate() {
        val action = SplashScreenFragmentDirections.actionSplashScreenFragmentToCountriesFragment()
        findNavController().navigate(action)
    }
}
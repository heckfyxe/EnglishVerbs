package com.heckfyxe.englishverbs.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.heckfyxe.englishverbs.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels {
        ViewModelProvider.AndroidViewModelFactory(
            requireActivity().application
        )
    }

    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        return binding.root
    }

}
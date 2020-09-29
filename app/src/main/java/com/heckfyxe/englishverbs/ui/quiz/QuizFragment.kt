package com.heckfyxe.englishverbs.ui.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.heckfyxe.englishverbs.R
import com.heckfyxe.englishverbs.databinding.QuizFragmentBinding

class QuizFragment : Fragment() {

    private val viewModel: QuizViewModel by viewModels {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                QuizViewModel(requireActivity().application, args.assetName) as T
        }
    }

    private val args: QuizFragmentArgs by navArgs()

    private lateinit var binding: QuizFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = QuizFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        initViews()
        return binding.root
    }

    private fun initViews() {
        binding.secondFormEditText.doAfterTextChanged {
            viewModel.onSecondFormChanged(it?.toString())
        }

        binding.thirdFormEditText.doAfterTextChanged {
            viewModel.onThirdFormChanged(it?.toString())
        }
    }

}
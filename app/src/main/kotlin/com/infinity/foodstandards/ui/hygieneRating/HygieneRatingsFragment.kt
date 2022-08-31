package com.infinity.foodstandards.ui.hygieneRating

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.infinity.foodstandards.databinding.HygieneRatingsFragmentBinding

class HygieneRatingsFragment : Fragment() {

    private var _binding: HygieneRatingsFragmentBinding? = null
    private val binding get() = requireNotNull(_binding) {
        "Hygiene ratings fragment UI accessed after onDestroyView"
    }

    private lateinit var viewModel: HygieneRatingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[HygieneRatingsViewModel::class.java]
        _binding = HygieneRatingsFragmentBinding.inflate(inflater, container, false);
        return binding.root
    }

}

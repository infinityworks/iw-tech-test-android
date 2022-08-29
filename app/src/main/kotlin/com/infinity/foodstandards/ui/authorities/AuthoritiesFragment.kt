package com.infinity.foodstandards.ui.authorities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.infinity.foodstandards.R
import com.infinity.foodstandards.databinding.AuthoritiesFragmentBinding
import com.infinity.foodstandards.model.LocalAuthoritiesResponse
import com.infinity.foodstandards.model.LocalAuthority

class AuthoritiesFragment : Fragment(), Observer<LocalAuthoritiesResponse> {

    private var _binding: AuthoritiesFragmentBinding? = null
    private val binding get() = requireNotNull(_binding) {
        "Authorities fragment UI accessed after onDestroyView"
    }

    private lateinit var viewModel: AuthoritiesViewModel
    private lateinit var localAuthorities: List<LocalAuthority>
    private lateinit var adapter: AuthorisesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[AuthoritiesViewModel::class.java]
        _binding = AuthoritiesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLocalAuthorities().observe(viewLifecycleOwner, this)

        adapter = AuthorisesAdapter {
            findNavController().navigate(R.id.action_authoritiesFragment_to_hygieneRatingsFragment)
        }
        binding.authoritiesRecycleView.adapter = adapter
    }

    override fun onChanged(authoritiesResponse: LocalAuthoritiesResponse?) {
        if (authoritiesResponse == null) {
            return
        }
        localAuthorities = authoritiesResponse.authorities
        adapter.updateData(localAuthorities)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

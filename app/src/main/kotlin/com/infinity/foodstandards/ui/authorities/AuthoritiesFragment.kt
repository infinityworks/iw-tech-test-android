package com.infinity.foodstandards.ui.authorities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.infinity.foodstandards.R
import com.infinity.foodstandards.model.LocalAuthority

class AuthoritiesFragment : Fragment() {

    private lateinit var viewModel: AuthoritiesViewModel
    private var localAuthorities: List<LocalAuthority> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[AuthoritiesViewModel::class.java]
        return ComposeView(requireContext()).apply {
            // Dispose the Composition when viewLifecycleOwner is destroyed
            setViewCompositionStrategy(
                ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner)
            )
            setContent {
                val apiResponse = viewModel.localAuthorities.observeAsState().value
                if (apiResponse != null) {
                    localAuthorities = apiResponse.authorities
                }
                MaterialTheme {
                    AuthorityList(localAuthorities) {
                        findNavController().navigate(R.id.action_authoritiesFragment_to_hygieneRatingsFragment)
                    }
                }

            }
        }
    }
}

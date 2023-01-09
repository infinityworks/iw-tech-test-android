package com.infinity.foodstandards.ui.authorities

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.infinity.foodstandards.R
import com.infinity.foodstandards.databinding.AuthoritiesFragmentBinding
import com.infinity.foodstandards.model.LocalAuthoritiesResponse
import com.infinity.foodstandards.model.LocalAuthority
import java.util.*

class AuthoritiesFragment : Fragment(), Observer<LocalAuthoritiesResponse> {

    private var _binding: AuthoritiesFragmentBinding? = null
    private val binding get() = requireNotNull(_binding) {
        "Authorities fragment UI accessed after onDestroyView"
    }

    private lateinit var viewModel: AuthoritiesViewModel
    private var localAuthorities: List<LocalAuthority> = listOf()
    private lateinit var adapter: AuthorisesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[AuthoritiesViewModel::class.java]
//        val nameState: State<String> = viewModel.localAuthorities.observeAsState("")
//        nameState.value
//        val list by nameState.value
//        val tasks by viewModel.status.collectAsState()
//        localAuthorities = listOf()

        return ComposeView(requireContext()).apply {
            // Dispose the Composition when viewLifecycleOwner is destroyed
            setViewCompositionStrategy(
                ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner)
            )
            setContent {
//                viewModel.getLocalAuthorities()

                val apiResponse = viewModel.localAuthorities.observeAsState().value
                if (apiResponse != null) {
                    localAuthorities = apiResponse.authorities
                }
                MaterialTheme {
                    AuthorityList(localAuthorities, {
                        findNavController().navigate(R.id.action_authoritiesFragment_to_hygieneRatingsFragment)
                    })
                }

            }
        }
//        viewModel = ViewModelProvider(this)[AuthoritiesViewModel::class.java]
//        _binding = AuthoritiesFragmentBinding.inflate(inflater, container, false)
//        return binding.root
    }
    @Composable
    fun Conversation(authority: List<LocalAuthority>) {
//        val recipes = viewModel.localAuthorities.observeAsState().value
//        if (recipes != null) {
//            for(recipe in recipes.authorities){
//                Log.d(TAG, "RECIPE: ${recipe.name}")
//            }
//        }
        LazyColumn {
            items(authority) { authority ->
                Text(text = authority.name)
            }
        }
    }
//
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLocalAuthorities().observe(viewLifecycleOwner, this)
//
//        adapter = AuthorisesAdapter ({
//            findNavController().navigate(R.id.action_authoritiesFragment_to_hygieneRatingsFragment)
//        })
//        binding.authoritiesRecycleView.adapter = adapter
    }
//
    override fun onChanged(authoritiesResponse: LocalAuthoritiesResponse?) {
        if (authoritiesResponse == null) {
            return
        }
//        localAuthorities = authoritiesResponse.authorities
//        if (localAuthorities != null) {
//            for(recipe in localAuthorities){
//                Log.d(TAG, "RECIPE: ${recipe.name}")
//            }
//        }
//        adapter.submitList(localAuthorities)
    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
}

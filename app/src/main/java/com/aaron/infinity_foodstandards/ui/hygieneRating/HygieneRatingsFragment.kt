package com.aaron.infinity_foodstandards.ui.hygieneRating

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aaron.infinity_foodstandards.R
import com.aaron.infinity_foodstandards.model.Establishment
import com.aaron.infinity_foodstandards.model.EstablishmentResponse

class HygieneRatingsFragment : Fragment() {

    private lateinit var viewModel: HygieneRatingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.hygiene_ratings_fragment, container, false)
    }

    @Deprecated("Use {@link #onViewCreated(View, Bundle)}")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HygieneRatingsViewModel::class.java)



    }
}

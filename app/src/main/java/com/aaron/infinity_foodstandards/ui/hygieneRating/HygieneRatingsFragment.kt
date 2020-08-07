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
import kotlinx.android.synthetic.main.hygiene_ratings_fragment.*


class HygieneRatingsFragment : Fragment() {

    private lateinit var viewModel: HygieneRatingsViewModel
    private lateinit var establishments: List<Establishment>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.hygiene_ratings_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HygieneRatingsViewModel::class.java)

        val id = arguments?.getInt("AuthorityId")

        //Get establishments info from API
        val establishmentsObserver = Observer<EstablishmentResponse> { newList ->
            establishments = newList.establishments

            //Count how many X star rated establishments
            val star5 = establishments.count {
                it.rating == "fhrs_5_en-gb"
            }

            val star4 = establishments.count {
                it.rating == "fhrs_4_en-gb"
            }

            val star3 = establishments.count {
                it.rating == "fhrs_3_en-gb"
            }

            val star2 = establishments.count {
                it.rating == "fhrs_2_en-gb"
            }

            val star1 = establishments.count {
                it.rating == "fhrs_1_en-gb"
            }

            val exempt = establishments.count {
                it.rating == "fhrs_exempt_en-gb"
            }

            val total: Double = star5 + star4 + star3 + star2 + star1 + exempt + 0.0

            /* Some (Scottish) establishments are rated Pass/Fail. */
            if (total == 0.0) {
                percentage5TextView.text = getString(
                    R.string.percentage_value,
                    0.0
                )
                percentage4TextView.text = getString(
                    R.string.percentage_value,
                    0.0
                )
                percentage3TextView.text = getString(
                    R.string.percentage_value,
                    0.0
                )
                percentage2TextView.text = getString(
                    R.string.percentage_value,
                    0.0
                )
                percentage1TextView.text = getString(
                    R.string.percentage_value,
                    0.0
                )
                percentageExemptTextView.text = getString(
                    R.string.percentage_value,
                    0.0
                )
                Toast.makeText(
                    context,
                    getString(R.string.no_star_ratings_for_establishments),
                    Toast.LENGTH_LONG
                ).show()
            } else {
                percentage5TextView.text = getString(
                    R.string.percentage_value,
                    (star5 / total) * 100
                )
                percentage4TextView.text = getString(
                    R.string.percentage_value,
                    (star4 / total) * 100
                )
                percentage3TextView.text = getString(
                    R.string.percentage_value,
                    (star3 / total) * 100
                )
                percentage2TextView.text = getString(
                    R.string.percentage_value,
                    (star2 / total) * 100
                )
                percentage1TextView.text = getString(
                    R.string.percentage_value,
                    (star1 / total) * 100
                )
                percentageExemptTextView.text = getString(
                    R.string.percentage_value,
                    (exempt / total) * 100
                )
            }

            //Hide the progress bar loader as we have the data
            progressBar.visibility = View.GONE
        }

        //Request to get the data, if the ID has come in the bundle
        if (id != null) {
            viewModel.getAllEstablishments(id).observe(viewLifecycleOwner, establishmentsObserver)
        } else {
            Toast.makeText(context, "No establishment selected", Toast.LENGTH_LONG).show()
        }

    }
}

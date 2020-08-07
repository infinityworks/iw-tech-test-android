package com.aaron.infinity_foodstandards

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aaron.infinity_foodstandards.ui.AuthorityListener
import com.aaron.infinity_foodstandards.ui.authorities.AuthoritiesFragment
import com.aaron.infinity_foodstandards.ui.hygieneRating.HygieneRatingsFragment

class MainActivity : AppCompatActivity(), AuthorityListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, AuthoritiesFragment.newInstance())
                .commitNow()
        }
    }

    override fun onAuthorityClick(authorityId: Int) {
        //Pass the authority ID to the ratings fragment
        val ratings = HygieneRatingsFragment()
        val args = Bundle()
        args.putInt("AuthorityId", authorityId)
        ratings.arguments = args

        val transaction = supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, ratings)
            addToBackStack("Ratings")
        }

        transaction.commit()
    }
}

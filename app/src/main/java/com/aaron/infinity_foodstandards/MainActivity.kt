package com.aaron.infinity_foodstandards

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aaron.infinity_foodstandards.ui.AuthorityListener
import com.aaron.infinity_foodstandards.ui.authorities.AuthoritiesFragment


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
        //TODO Pass the authority ID to the ratings fragment
//        val args = Bundle()
//        args.putInt("AuthorityId", authorityId)
//
//        val transaction = supportFragmentManager.beginTransaction().apply {
//            addToBackStack("Ratings")
//        }
//
//        transaction.commit()
    }
}

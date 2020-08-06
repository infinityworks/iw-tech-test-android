package com.aaron.infinity_foodstandards.ui.authorities

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aaron.infinity_foodstandards.R
import com.aaron.infinity_foodstandards.model.LocalAuthoritiesResponse
import com.aaron.infinity_foodstandards.model.LocalAuthority
import com.aaron.infinity_foodstandards.ui.AuthorityListener
import kotlinx.android.synthetic.main.authorities_fragment.*

class AuthoritiesFragment : Fragment(), Observer<LocalAuthoritiesResponse> {

    private lateinit var viewModel: AuthoritiesViewModel
    private lateinit var localAuthorities: List<LocalAuthority>
    private lateinit var adapter: AuthorisesAdapter
    private var listener: AuthorityListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(AuthoritiesViewModel::class.java)
        return inflater.inflate(R.layout.authorities_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLocalAuthorities().observe(viewLifecycleOwner, this)
    }

    interface OnItemClickListener {
        fun onItemClicked(position: Int, view: View)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? AuthorityListener
        if (listener == null) {
            throw ClassCastException("$context must implement AuthorityClickListener")
        }
    }

    //Item click helper function, provides callback to get recycler view position
    private fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener) {
        this.addOnChildAttachStateChangeListener(object :
            RecyclerView.OnChildAttachStateChangeListener {

            override fun onChildViewDetachedFromWindow(view: View) {
                view.setOnClickListener(null)
            }

            override fun onChildViewAttachedToWindow(view: View) {
                view.setOnClickListener {
                    val holder = getChildViewHolder(view)
                    onClickListener.onItemClicked(holder.adapterPosition, view)
                }
            }
        })
    }

    override fun onChanged(authoritiesResponse: LocalAuthoritiesResponse?) {
        if (authoritiesResponse == null) {
            return
        }

        //Assign list from observer to the recycler view adapter
        localAuthorities = authoritiesResponse.authorities

        authoritiesRecycleView.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        adapter =
            AuthorisesAdapter(
                localAuthorities
            )
        authoritiesRecycleView.adapter = adapter

        //Use the on click listener to listen for on click events
        authoritiesRecycleView.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                listener?.onAuthorityClick(localAuthorities[position].id)
            }
        })
    }

    companion object {
        fun newInstance() = AuthoritiesFragment()
    }
}

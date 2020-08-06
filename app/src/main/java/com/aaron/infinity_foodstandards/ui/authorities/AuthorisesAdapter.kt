package com.aaron.infinity_foodstandards.ui.authorities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aaron.infinity_foodstandards.R
import com.aaron.infinity_foodstandards.model.LocalAuthority

class AuthorisesAdapter(private val authority: List<LocalAuthority>) :
    RecyclerView.Adapter<AuthorisesAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.local_authority_item, parent, false)
        return ItemViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindItems(authority[position])
    }

    override fun getItemCount(): Int {
        return authority.size
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(authority: LocalAuthority) {
            //Set title of authority in recycler view
            val title = itemView.findViewById(R.id.authorityNameTextView) as TextView
            title.text = authority.name
        }
    }
}
package com.infinity.foodstandards.ui.authorities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.infinity.foodstandards.databinding.LocalAuthorityItemBinding
import com.infinity.foodstandards.model.LocalAuthority

class AuthorisesAdapter(
    private val onItemSelected: (LocalAuthority) -> Unit,
    diffUtil: DiffUtil.ItemCallback<LocalAuthority> = LocalAuthorityDiffUtil()
) : ListAdapter<LocalAuthority, AuthorisesAdapter.ItemViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            LocalAuthorityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding, onItemSelected)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindItems(getItem(position))
    }

    class ItemViewHolder(
        private val binding: LocalAuthorityItemBinding,
        private val onItemSelected: (LocalAuthority) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindItems(authority: LocalAuthority) {
            // Set title and click listener
            binding.authorityNameTextView.text = authority.name
            itemView.setOnClickListener { onItemSelected(authority) }
        }
    }
}

class LocalAuthorityDiffUtil : DiffUtil.ItemCallback<LocalAuthority>() {
    override fun areItemsTheSame(oldItem: LocalAuthority, newItem: LocalAuthority): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: LocalAuthority, newItem: LocalAuthority): Boolean =
        oldItem == newItem
}
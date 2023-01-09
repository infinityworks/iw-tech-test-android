package com.infinity.foodstandards.ui.authorities

import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.infinity.foodstandards.model.LocalAuthority

class AuthorisesAdapter(
    private val onItemSelected: (LocalAuthority) -> Unit,
    diffUtil: DiffUtil.ItemCallback<LocalAuthority> = LocalAuthorityDiffUtil()
) : ListAdapter<LocalAuthority, AuthorisesAdapter.ItemViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ComposeView(parent.context), onItemSelected)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindItems(getItem(position))
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }

    class ItemViewHolder(
        private val composeView: ComposeView,
        private val onItemSelected: (LocalAuthority) -> Unit
    ) : RecyclerView.ViewHolder(composeView) {
        fun bindItems(authority: LocalAuthority) {
            // Set title and click listener
//            binding.authorityNameTextView.text = authority.name
//            itemView.setOnClickListener { onItemSelected(authority) }
//            composeView.setContent {
//                MaterialTheme {
//                    AuthorityList(authority)
//                }
//            }
        }
    }
}

class LocalAuthorityDiffUtil : DiffUtil.ItemCallback<LocalAuthority>() {
    override fun areItemsTheSame(oldItem: LocalAuthority, newItem: LocalAuthority): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: LocalAuthority, newItem: LocalAuthority): Boolean =
        oldItem == newItem
}
package com.example.githubviewerapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubviewerapp.databinding.ItemUserBinding
import com.example.githubviewerapp.network.model.Item

class UserAdapter(val onClick: (Item) -> Unit) : ListAdapter<Item, UserAdapter.ViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id   // 👈 ID orqali solishtirish
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(user: Item) = with(binding) {

            binding.root.setOnClickListener {
                onClick(user)
            }
            // Avatar load
            Glide.with(ivAvatar)
                .load(user.avatar_url)
                .into(ivAvatar)

            // Username
            tvUsername.text = user.login

//            // Full name
//            tvFullname.text = user.following_url
//
//            // Bio
//            tvBio.text = user.subscriptions_url
//
//            // Location
//            tvLocation.text = user.organizations_url
//
//            // Public repos
//            tvRepoCount.text = user.repos_url
//
//            // Followers
//            tvFollowers.text = user.followers_url
        }
    }
}
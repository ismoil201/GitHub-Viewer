package com.example.githubviewerapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.githubviewerapp.adapter.RepoAdapter.*
import com.example.githubviewerapp.databinding.ItemRepoBinding
import com.example.githubviewerapp.network.model.Repo

class RepoAdapter() : ListAdapter<Repo, RepoViewHolder>(object : DiffUtil.ItemCallback<Repo>(){
    override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
       return oldItem.id == newItem.id
    }

    override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
        return oldItem == newItem
    }
}

) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RepoViewHolder {

        return RepoViewHolder(ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(
        holder: RepoViewHolder,
        position: Int
    ) {
        return holder.onBind(currentList[position])
    }


    inner class RepoViewHolder(val binding : ItemRepoBinding) : RecyclerView.ViewHolder(binding.root)
    {

        fun onBind(repo : Repo){
            binding.tvRepoName.text = repo.name
            binding.tvDescription.text = repo.description
            binding.tvStarCount.text = repo.stargazers_count.toString()
        }

    }
}
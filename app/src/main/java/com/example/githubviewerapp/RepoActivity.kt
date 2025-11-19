package com.example.githubviewerapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.githubviewerapp.adapter.RepoAdapter
import com.example.githubviewerapp.databinding.ActivityRepoBinding

class RepoActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRepoBinding
    private  lateinit var repoAdapter : RepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRepoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        repoAdapter = RepoAdapter()


    }
}
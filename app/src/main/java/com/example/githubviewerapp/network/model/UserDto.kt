package com.example.githubviewerapp.network.model

data class UserDto(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)
package com.example.githubviewerapp.network.model

data class Permissions(
    val admin: Boolean,
    val pull: Boolean,
    val push: Boolean
)
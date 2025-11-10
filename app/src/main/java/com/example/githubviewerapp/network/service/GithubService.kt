package com.example.githubviewerapp.network.service

import com.example.githubviewerapp.network.model.Repo
import com.example.githubviewerapp.network.model.UserDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("users/{username}/repos")
    fun getUserListRepos(@Path("username") username : String): Call<List<Repo>>

    @GET ("search/users")
    fun getSearchUser(@Query("q") query: String) : Call<UserDto>
}
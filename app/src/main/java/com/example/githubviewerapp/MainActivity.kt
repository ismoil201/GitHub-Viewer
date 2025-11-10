package com.example.githubviewerapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.githubviewerapp.network.model.Repo
import com.example.githubviewerapp.network.model.UserDto
import com.example.githubviewerapp.network.service.GithubService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val githubService = retrofit.create(GithubService::class.java)


        githubService.getUserListRepos("ismoil201").enqueue(
            object : Callback<List<Repo>> {
                override fun onResponse(p0: Call<List<Repo>?>, response: Response<List<Repo>?>) {


                    Log.d("git", "getUserListRepository"  +response.body().toString())
                }

                override fun onFailure(p0: Call<List<Repo>?>, p1: Throwable) {
                }

            }
        )

        githubService.getSearchUser("ismoil2").enqueue(object : Callback<UserDto> {
            override fun onResponse(p0: Call<UserDto?>, response: Response<UserDto?>) {

                Log.d("git", "search User : "+response.body().toString())

            }

            override fun onFailure(p0: Call<UserDto?>, p1: Throwable) {
            }

        })

    }
}
package com.example.githubviewerapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.githubviewerapp.adapter.UserAdapter
import com.example.githubviewerapp.databinding.ActivityMainBinding
import com.example.githubviewerapp.network.model.UserDto
import com.example.githubviewerapp.network.service.GithubService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {


    private lateinit var adapter: UserAdapter
    private lateinit var binding: ActivityMainBinding

    private val handler = Handler(Looper.getMainLooper())
    private var searchFor: String = ""
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val runnable = Runnable {
            searchUser()
        }
        binding.editTextSearch.addTextChangedListener {

            searchFor = it.toString()

            handler.removeCallbacks(runnable)
            handler.postDelayed(
                runnable,
                300,

                )


        }


    }

//    private fun searchRepo(){
//        val githubService = retrofit.create(GithubService::class.java)
//
//        githubService.getUserListRepos("ismoil201").enqueue(
//            object : Callback<List<Repo>> {
//                override fun onResponse(p0: Call<List<Repo>?>, response: Response<List<Repo>?>) {
//
//
//                    Log.d("git", "getUserListRepository"  +response.body().toString())
//                }
//
//                override fun onFailure(p0: Call<List<Repo>?>, p1: Throwable) {
//                }
//
//            }
//        )
//    }

    private fun searchUser() {
        val githubService = retrofit.create(GithubService::class.java)

        githubService.getSearchUser(searchFor).enqueue(object : Callback<UserDto> {
            override fun onResponse(p0: Call<UserDto?>, response: Response<UserDto?>) {

                Log.d("git", "search User : " + response.body().toString())

                val list = response.body()?.items

                adapter = UserAdapter{
                    val intent = Intent(this@MainActivity, RepoActivity::class.java)
                    intent.putExtra("username", it.login)
                    startActivity(intent)

                }

                adapter.submitList(list)
                binding.recyclerView.adapter = adapter

            }

            override fun onFailure(p0: Call<UserDto?>, p1: Throwable) {

                Toast.makeText(this@MainActivity, "Error app", Toast.LENGTH_SHORT).show()

            }

        })
    }
}
package com.example.mvvm.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.R
import com.example.mvvm.data.models.User
import com.example.mvvm.ui.viewmodel.GithubViewModel
import com.example.networking_kotlin.UserAdapter

class MainActivity : AppCompatActivity() {

    lateinit var userRv: RecyclerView
    lateinit var searchView: SearchView
    val vm by lazy {
        ViewModelProvider(this).get(GithubViewModel::class.java)
    }
    val list = arrayListOf<User>()
    val adapter = UserAdapter(list)
    val originalList = arrayListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userRv = findViewById(R.id.usersRv)

        userRv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        searchView = findViewById(R.id.searchview)

        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    findUsers(it)
                }
                return true
            }

            override fun onQueryTextChange(newtext: String?): Boolean {
                newtext?.let {
                    findUsers(it)
                }
                return true
            }

        })

        searchView.setOnCloseListener {
            list.clear()
            list.addAll(originalList) //Another way-> list.addAll(vm.user.value)
       //     vm.users.value?.let { list.addAll(it) }
            adapter.notifyDataSetChanged()
            return@setOnCloseListener true
        }


        vm.fetchUsers()

        vm.users.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                list.addAll(it)
                originalList.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })

    }

    private fun findUsers(query: String) {
        vm.searchUsers(query).observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                list.clear()
                list.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })

    }
}
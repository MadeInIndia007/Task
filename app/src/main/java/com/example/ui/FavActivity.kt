package com.example.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.ui.MainActivity.Companion.mainViewModel
import com.example.viewModel.MainViewModel
import com.example.adapter.FavAdapter
import kotlinx.android.synthetic.main.activity_fav.*

class FavActivity : AppCompatActivity() {
    val adapter = FavAdapter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fav)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        initView()
        ll_layout.adapter=adapter

    }

    private fun initView() {
        mainViewModel.mAllUsers.observe(this, Observer {
            Log.e("reeeee", it.toString())
            if (it.size > 0) {
                adapter.setProfiles(it)
            }
        })
    }


}

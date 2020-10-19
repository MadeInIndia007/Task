package com.example.viewModel

import android.annotation.SuppressLint
import android.app.Application
import android.graphics.ColorSpace
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.model.Model

import com.example.room.Enitity
import com.example.moviesdemo.util.RetrofitUtil
import com.example.respository.MainRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var userRepository: MainRepository =
        MainRepository(application)
    var mAllUsers: LiveData<List<Enitity>>
    var error = MutableLiveData<Throwable>()
    val onSuccess = MutableLiveData<Model>()


    @SuppressLint("CheckResult")
    fun getData() {
        RetrofitUtil.apiService().getData(

        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe({ onSuccess(it) }, { onFailure(it) })

    }

    private fun onFailure(it: Throwable?) {
        error.postValue(
            it
        )
    }

    private fun onSuccess(it:Model?) {
        onSuccess.postValue(it)

    }

    fun insert(md: Enitity?) {
        userRepository.insert(md)
    }




    init {
        mAllUsers = userRepository.getAllUsers()

    }
}
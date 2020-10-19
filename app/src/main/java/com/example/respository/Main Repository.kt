package com.example.respository


import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData


import com.example.moviesdemo.room.AppDataBase
import com.example.room.Enitity
import com.example.moviesdemo.room.Dao


class MainRepository(application: Application) {

     lateinit var dao: Dao
     var mAllUsers: LiveData<List<Enitity>>? = null

     init {
         val database: AppDataBase? = AppDataBase.getInstance(application)
         if (database != null) {
             dao = database.movieDao()
             mAllUsers = dao.getAll()
         }

     }

     fun getAllUsers(): LiveData<List<Enitity>> {
         return dao.getAll()
     }

     fun insert(md: Enitity?) {
         InsertAsyncTask(dao).execute(md)
     }

     private class InsertAsyncTask internal constructor(dao: Dao) :
         AsyncTask<Enitity?, Void?, Void?>() {
         private val mAsyncTaskDao: Dao

         override fun doInBackground(vararg p0: Enitity?): Void? {
             p0[0]?.let { mAsyncTaskDao.insert(it) }

             return null
         }

         init {
             mAsyncTaskDao = dao
         }
     }


}
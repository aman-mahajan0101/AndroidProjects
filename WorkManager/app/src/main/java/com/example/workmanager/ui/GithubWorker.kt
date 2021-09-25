package com.example.workmanager.ui

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.workmanager.networking.Client
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GithubWorker(val context: Context,val param : WorkerParameters): CoroutineWorker(context,param) {

    override suspend fun doWork(): Result {
        val reponse = withContext(Dispatchers.IO) { Client.api.getUsers() }
        return if (reponse.isSuccessful) {
            Log.i("Worker Request","Work Completed")
            Result.success()
        }else{
            Log.i("Worker Request","Work Restarted")
            Result.retry()
        }
    }

}
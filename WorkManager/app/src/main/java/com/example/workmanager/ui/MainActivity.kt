package com.example.workmanager.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.*
import com.example.workmanager.R
import com.google.android.material.button.MaterialButton
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    lateinit var workerbtn : MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        workerbtn=findViewById(R.id.workerbtn)

        workerbtn.setOnClickListener {
            setupGithubWorker()
        }
    }

    private fun setupGithubWorker() {
        val constraints  = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .build()
        val worker = OneTimeWorkRequestBuilder<GithubWorker>()
            .setInitialDelay(4,TimeUnit.SECONDS)
            .setConstraints(constraints)
            .build()

        //Another way to do this
//        val worker = PeriodicWorkRequestBuilder<GithubWorker>(1,TimeUnit.DAYS)
//            .setInitialDelay(8,TimeUnit.HOURS)
//            .setConstraints(constraints)
//            .build()

        WorkManager.getInstance(this).enqueue(worker)
    }
}
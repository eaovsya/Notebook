package com.example.notebook.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.notebook.NotebookApp
import com.example.notebook.R

class MainActivity : AppCompatActivity() {

    val mainActivityComponent: MainActivityComponent by lazy {
        (application as NotebookApp).appComponent.mainActivityComponent()
            .create(supportFragmentManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
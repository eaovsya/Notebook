package com.example.notebook

import android.app.Application
import com.example.notebook.di.AppComponent
import com.example.notebook.di.DaggerAppComponent

class NotebookApp : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }
}
package com.example.notebook.di

import android.app.Application
import com.example.notebook.db.DatabaseModule
import com.example.notebook.main.MainActivityComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }

    fun mainActivityComponent(): MainActivityComponent.Factory
}
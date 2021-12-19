package com.example.notebook.details.di

import com.example.notebook.details.DetailsContract
import com.example.notebook.details.DetailsPresenter
import com.example.notebook.details.DetailsRouter
import com.example.notebook.di.FragmentScope
import dagger.Binds
import dagger.Module

@Module
abstract class DetailsModule {
    @Binds
    @FragmentScope
    abstract fun provideRouter(router: DetailsRouter): DetailsContract.Router

    @Binds
    @FragmentScope
    abstract fun providePresenter(presenter: DetailsPresenter): DetailsContract.Presenter
}
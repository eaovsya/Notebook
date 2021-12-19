package com.example.notebook.empty.di

import com.example.notebook.di.FragmentScope
import com.example.notebook.empty.EmptyContract
import com.example.notebook.empty.EmptyInteractor
import com.example.notebook.empty.EmptyPresenter
import com.example.notebook.empty.EmptyRouter
import dagger.Binds
import dagger.Module

@Module
abstract class EmptyModule {
    @Binds
    @FragmentScope
    abstract fun provideRouter(router: EmptyRouter): EmptyContract.Router

    @Binds
    @FragmentScope
    abstract fun provideInteractor(interactor: EmptyInteractor): EmptyContract.Interactor

    @Binds
    @FragmentScope
    abstract fun providePresenter(presenter: EmptyPresenter): EmptyContract.Presenter
}
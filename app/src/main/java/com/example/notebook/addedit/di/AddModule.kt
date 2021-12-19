package com.example.notebook.addedit.di

import com.example.notebook.addedit.AddEditContract
import com.example.notebook.addedit.AddEditInteractor
import com.example.notebook.addedit.AddEditPresenter
import com.example.notebook.addedit.AddEditRouter
import com.example.notebook.di.FragmentScope
import dagger.Binds
import dagger.Module

@Module
abstract class AddModule {
    @Binds
    @FragmentScope
    abstract fun provideRouter(editRouter: AddEditRouter): AddEditContract.Router

    @Binds
    @FragmentScope
    abstract fun provideInteractor(editInteractor: AddEditInteractor): AddEditContract.Interactor

    @Binds
    @FragmentScope
    abstract fun providePresenter(editPresenter: AddEditPresenter): AddEditContract.Presenter
}
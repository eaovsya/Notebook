package com.example.notebook.contacts.di

import com.example.notebook.contacts.ContactsContract
import com.example.notebook.contacts.ContactsInteractor
import com.example.notebook.contacts.ContactsPresenter
import com.example.notebook.contacts.ContactsRouter
import com.example.notebook.di.FragmentScope
import dagger.Binds
import dagger.Module

@Module
abstract class ContactsModule {
    @Binds
    @FragmentScope
    abstract fun provideRouter(router: ContactsRouter): ContactsContract.Router

    @Binds
    @FragmentScope
    abstract fun provideInteractor(interactor: ContactsInteractor): ContactsContract.Interactor

    @Binds
    @FragmentScope
    abstract fun providePresenter(presenter: ContactsPresenter): ContactsContract.Presenter
}
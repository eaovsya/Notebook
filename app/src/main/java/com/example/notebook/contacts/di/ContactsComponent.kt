package com.example.notebook.contacts.di

import com.example.notebook.contacts.ContactsFragment
import com.example.notebook.di.FragmentScope
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [ContactsModule::class])
interface ContactsComponent {

    @Subcomponent.Builder
    interface Factory {
        fun create(): ContactsComponent
    }

    fun inject(target: ContactsFragment)

}
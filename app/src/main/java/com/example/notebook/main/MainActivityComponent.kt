package com.example.notebook.main

import androidx.fragment.app.FragmentManager
import com.example.notebook.addedit.di.AddEditComponent
import com.example.notebook.contacts.di.ContactsComponent
import com.example.notebook.details.di.DetailsComponent
import com.example.notebook.di.ActivityScope
import com.example.notebook.empty.di.EmptyComponent
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent()
interface MainActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance fragmentManager: FragmentManager): MainActivityComponent
    }

    fun contactsComponent(): ContactsComponent.Factory
    fun detailsComponent(): DetailsComponent.Factory
    fun addComponent(): AddEditComponent.Factory
    fun emptyComponent(): EmptyComponent.Factory
}
package com.example.notebook.addedit.di

import com.example.notebook.addedit.add.AddFragment
import com.example.notebook.addedit.edit.EditFragment
import com.example.notebook.di.FragmentScope
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [AddModule::class])
interface AddEditComponent {

    @Subcomponent.Builder
    interface Factory {
        fun create(): AddEditComponent
    }

    fun inject(target: AddFragment)
    fun inject(target: EditFragment)
}
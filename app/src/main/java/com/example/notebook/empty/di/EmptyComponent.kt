package com.example.notebook.empty.di

import com.example.notebook.di.FragmentScope
import com.example.notebook.empty.EmptyFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [EmptyModule::class])
interface EmptyComponent {

    @Subcomponent.Builder
    interface Factory {
        fun create(): EmptyComponent
    }

    fun inject(target: EmptyFragment)

}
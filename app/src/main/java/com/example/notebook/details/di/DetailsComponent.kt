package com.example.notebook.details.di

import com.example.notebook.details.DetailsFragment
import com.example.notebook.di.FragmentScope
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [DetailsModule::class])
interface DetailsComponent {

    @Subcomponent.Builder
    interface Factory {
        fun create(): DetailsComponent
    }

    fun inject(target: DetailsFragment)

}
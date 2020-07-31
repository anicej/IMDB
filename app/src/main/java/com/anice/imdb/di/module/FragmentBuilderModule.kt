package com.anice.imdb.di.module

import com.anice.imdb.view.fragment.DetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Define all your fragments here
 */

@Module
interface FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeDetailsFragment(): DetailsFragment

}
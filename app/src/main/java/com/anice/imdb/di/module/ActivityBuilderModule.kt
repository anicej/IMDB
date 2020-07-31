package com.anice.imdb.di.module

import com.anice.imdb.view.activity.main_page.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Define all your activities here
 */

@Module(includes = [FragmentBuilderModule::class])
interface ActivityBuilderModule {

    @ContributesAndroidInjector
    fun contributeTodoActivity(): MainActivity
}
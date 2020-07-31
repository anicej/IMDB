package com.anice.imdb.application

import android.app.Activity
import android.app.Application
import android.content.Context
import com.anice.imdb.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasFragmentInjector
import javax.inject.Inject


class CoreApplication : Application(), HasActivityInjector, HasFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var dispatchingAndroidSupportInjector: DispatchingAndroidInjector<android.app.Fragment>

    init {
        instance = this
    }

    companion object {
        private var instance: CoreApplication? = null
        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }

    override fun activityInjector() = dispatchingAndroidInjector
    override fun fragmentInjector(): AndroidInjector<android.app.Fragment>? =
        dispatchingAndroidSupportInjector
}
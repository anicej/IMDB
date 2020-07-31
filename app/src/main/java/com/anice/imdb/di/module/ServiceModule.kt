package com.anice.imdb.di.module

import com.anice.imdb.domain.service.IMDBService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ServiceModule {

    @Provides
    @Singleton
    fun provideIMDBService(retrofit: Retrofit): IMDBService {
        return retrofit.create(IMDBService::class.java)
    }
}
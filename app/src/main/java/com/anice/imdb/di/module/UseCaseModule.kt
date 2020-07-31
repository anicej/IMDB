package com.anice.imdb.di.module

import com.anice.imdb.data.mapper.MainIMDBApiResponseMapper
import com.anice.imdb.data.repository.IMDBRepository
import com.anice.imdb.data.usecase.IMDBUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun provideIMDBUseCase(
        repository: IMDBRepository,
        mapper: MainIMDBApiResponseMapper
    ): IMDBUseCase {
        return IMDBUseCase(repository, mapper)
    }
}
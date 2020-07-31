package com.anice.imdb.domain.repository

import com.anice.imdb.data.model.Detail
import com.anice.imdb.data.model.MainIMDBModel
import com.anice.imdb.domain.remote.response.ApiResponse
import com.anice.imdb.domain.repository.base.IRepository
import com.anice.imdb.domain.service.IMDBService

abstract class BaseMainRepository(service: IMDBService) : IRepository {

    abstract suspend fun getMainList(): ApiResponse<MainIMDBModel>
    abstract suspend fun getDetails(id: String): ApiResponse<Detail>?


}
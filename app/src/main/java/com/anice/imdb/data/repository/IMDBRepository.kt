package com.anice.imdb.data.repository

import com.anice.imdb.data.model.Detail
import com.anice.imdb.data.model.MainIMDBModel
import com.anice.imdb.domain.remote.response.ApiResponse
import com.anice.imdb.domain.repository.BaseMainRepository
import com.anice.imdb.domain.service.IMDBService
import javax.inject.Inject

class IMDBRepository @Inject constructor(private val service: IMDBService) :
    BaseMainRepository(service) {

    override suspend fun getMainList(): ApiResponse<MainIMDBModel> {
        return handleRequest { service.getMainList() }
    }

    override suspend fun getDetails(id: String): ApiResponse<Detail>? {
        return handleRequest { service.getDetails(id) }
    }
}
package com.anice.imdb.data.mapper

import com.anice.imdb.data.mapper.base.BaseMapper
import com.anice.imdb.data.model.MainIMDBModel
import com.anice.imdb.domain.entity.IMDB
import com.anice.imdb.domain.remote.response.ApiResponse
import com.anice.imdb.domain.remote.response.ErrorResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainIMDBApiResponseMapper @Inject constructor(private val mapper: MainIMDBToModelMapper) :
    BaseMapper<ApiResponse<IMDB>, ApiResponse<MainIMDBModel>>() {

    override fun map(entity: ApiResponse<IMDB>?): ApiResponse<MainIMDBModel>? {
        entity?.let {
            if (it.isSuccessful)
                return ApiResponse.Success(mapper.map(it.data))
            else
                return ApiResponse.Failure(it.errorResponse)
        }
        return ApiResponse.Failure(ErrorResponse())
    }
}
package com.anice.imdb.data.usecase

import android.util.Log
import com.anice.imdb.data.mapper.MainIMDBApiResponseMapper
import com.anice.imdb.data.model.Detail
import com.anice.imdb.data.model.MainIMDBModel
import com.anice.imdb.data.repository.IMDBRepository
import com.anice.imdb.domain.remote.response.ApiResponse
import com.anice.imdb.domain.usecase.base.BaseUseCase
import javax.inject.Inject

class IMDBUseCase @Inject constructor(
    private val repository: IMDBRepository,
    private val mapper: MainIMDBApiResponseMapper
) : BaseUseCase(repository) {

    suspend fun getMainList(): ApiResponse<MainIMDBModel>? {
        Log.e("AAAAAAA,", "SDdcfdsfvs")
        val response = repository.getMainList()
        return response
//        mapper.map(response)!!
    }

    suspend fun getDetails(id: String): ApiResponse<Detail>? {
        val response = repository.getDetails(id)
        return response
    }
}
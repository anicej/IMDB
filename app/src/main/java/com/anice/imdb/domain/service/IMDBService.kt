package com.anice.imdb.domain.service

import com.anice.imdb.data.model.Detail
import com.anice.imdb.data.model.MainIMDBModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IMDBService {

    @GET("?apikey=3e974fca&s=batman")
    suspend fun getMainList(): Response<MainIMDBModel>

    @GET("?apikey=3e974fca")
    suspend fun getDetails(@Query("i") Id: String): Response<Detail>

}
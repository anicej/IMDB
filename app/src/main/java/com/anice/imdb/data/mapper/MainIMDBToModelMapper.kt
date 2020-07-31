package com.anice.imdb.data.mapper

import com.anice.imdb.data.mapper.base.BaseMapper
import com.anice.imdb.data.model.MainIMDBModel
import com.anice.imdb.domain.entity.IMDB
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainIMDBToModelMapper @Inject constructor() : BaseMapper<IMDB, MainIMDBModel>() {

    override fun map(entity: IMDB?): MainIMDBModel? {
        entity?.let {
//            return MainIMDBModel(it.completed, it.id, it.title)
            return null
        }
        return null
    }
}
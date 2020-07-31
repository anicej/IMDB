package com.anice.imdb.domain.entity

data class IMDB(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)
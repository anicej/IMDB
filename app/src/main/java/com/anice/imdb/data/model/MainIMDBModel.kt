package com.anice.imdb.data.model

data class MainIMDBModel(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)
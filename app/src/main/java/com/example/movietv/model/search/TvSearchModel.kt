package com.example.movietv.model.search

import java.io.Serializable

data class TvSearchModel(
    val score: Double,
    val show: Show
) : Serializable
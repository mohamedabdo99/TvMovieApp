package com.example.movietv.model.search

import java.io.Serializable

data class Show(
    val id: Int,
    val image: Image,
    val language: String,
    val name: String,
    val url: String,
) : Serializable
package com.example.movietv.model

import java.io.Serializable

data class TvShowModel(
        val id : Int,
        val image : Image,
        val name : String,
        val url : String,
)  : Serializable {
    data class Image(
        val medium : String,
        val original : String,
    ) : Serializable
}
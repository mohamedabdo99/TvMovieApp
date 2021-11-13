package com.example.movietv.repo

import com.example.movietv.data.remote.TVShowApi
import javax.inject.Inject

class TvShowRepository @Inject constructor(private val apiTvShow: TVShowApi)
{
    suspend fun getTvShows() = apiTvShow.getTvShow()
}
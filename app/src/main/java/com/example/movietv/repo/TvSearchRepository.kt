package com.example.movietv.repo

import com.example.movietv.data.remote.TVShowApi
import javax.inject.Inject

class TvSearchRepository @Inject constructor(private val apiTvShow: TVShowApi) {
    suspend fun getTvSearch(search : String) = apiTvShow.getSearch(search)
}
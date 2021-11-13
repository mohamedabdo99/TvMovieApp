package com.example.movietv.data.remote

import com.example.movietv.model.TvSearchResponse
import com.example.movietv.model.TvShowResponse
import com.example.movietv.util.constant.Constant.END_POINT
import com.example.movietv.util.constant.Constant.END_POINT2
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TVShowApi {

    @GET(END_POINT)
    suspend fun getTvShow(): Response<TvShowResponse>

    @GET(END_POINT2)
    suspend fun getSearch(
        @Query("q") q: String
    ): Response<TvSearchResponse>
}
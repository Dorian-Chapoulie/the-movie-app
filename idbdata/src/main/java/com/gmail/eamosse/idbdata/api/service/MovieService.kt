package com.gmail.eamosse.idbdata.api.service

import com.gmail.eamosse.idbdata.api.response.CategoryResponse
import com.gmail.eamosse.idbdata.api.response.DiscoverResponse
import com.gmail.eamosse.idbdata.api.response.TokenResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface MovieService {
    @GET("authentication/token/new")
    suspend fun getToken(): Response<TokenResponse>

    @GET("/3/genre/movie/list")
    suspend fun getCategories(): Response<CategoryResponse>

    @GET("/3/discover/movie")
    suspend fun getDiscover(@Query("with_genres") withGenres:Int, @Query("page") page:Int = 1): Response<DiscoverResponse>

}
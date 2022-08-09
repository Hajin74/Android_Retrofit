package com.example.rvretrofit

import retrofit2.Call
import retrofit2.http.GET

interface SongRetrofitInterface {
    @GET("/songs")
    fun getSongList(): Call<SongResponse>
}
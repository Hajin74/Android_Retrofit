package com.example.rvretrofit

import com.google.gson.annotations.SerializedName

data class SongResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: SongResult
)

data class SongResult(
    @SerializedName("songs") val songs: List<SongList>
)

data class SongList(
    @SerializedName("title") val title: String,
    @SerializedName("singer") val singer: String
)

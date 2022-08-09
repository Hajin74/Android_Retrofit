package com.example.rvretrofit

interface SongView {
    fun onGetSongListSuccess(songList: SongResponse)
    fun onGetSongListFailure()
}
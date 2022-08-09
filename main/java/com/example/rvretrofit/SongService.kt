package com.example.rvretrofit

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SongService {
    private lateinit var songView: SongView

    fun setSongView(songView: SongView) {
        this.songView = songView
    }

    fun getSongList() {
        val songService = getRetrofit().create(SongRetrofitInterface::class.java)

        songService.getSongList().enqueue(object: Callback<SongResponse> {
            override fun onResponse(call: Call<SongResponse>, response: Response<SongResponse>) {
                if(response != null) {
                    val songList: SongResponse = response.body()!!

                    when(songList.code) {
                        1000 -> songView.onGetSongListSuccess(songList)
                        else -> songView.onGetSongListFailure()
                    }
                }
            }

            override fun onFailure(call: Call<SongResponse>, t: Throwable) {
                Log.d("getSongList() / ", t.message.toString())
            }
        })

        Log.d("getSongList() / ", "SongService, 메소드")
    }
}
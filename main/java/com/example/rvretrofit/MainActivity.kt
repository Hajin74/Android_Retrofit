package com.example.rvretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rvretrofit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), SongView {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.listRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.listRv.setHasFixedSize(true)
        getSongList()
    }

    private fun getSongList() {
        val songService = SongService()
        songService.setSongView(this)
        songService.getSongList()
    }

    override fun onGetSongListSuccess(songList: SongResponse) {
        binding.listRv.adapter = SongRVAdapter(songList.result.songs)
        Toast.makeText(this, "곡 목록을 불러오는데에 성공했습니다", Toast.LENGTH_SHORT).show()

    }

    override fun onGetSongListFailure() {
        Toast.makeText(this, "곡 목록을 불러오는데에 실패했습니다", Toast.LENGTH_SHORT).show()
    }
}
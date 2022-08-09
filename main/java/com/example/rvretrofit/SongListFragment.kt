package com.example.rvretrofit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rvretrofit.databinding.FragmentSongListBinding

class SongListFragment : Fragment(), SongView {

    private lateinit var binding: FragmentSongListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSongListBinding.inflate(layoutInflater)

        binding.songListRv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.songListRv.setHasFixedSize(true)
        getSongList()

        return binding.root
    }
    
    private fun getSongList() {
        val songService = SongService()
        songService.setSongView(this)
        songService.getSongList()
        
        Log.d("getSongList() / ", "SongListFragment에서 메소드")
    }

    override fun onGetSongListSuccess(songList: SongResponse) {
        binding.songListRv.adapter = SongRVAdapter(songList.result.songs)
        Toast.makeText(activity, "곡 목록을 불러오는데에 성공했습니다", Toast.LENGTH_SHORT).show()
    }

    override fun onGetSongListFailure() {
        Toast.makeText(activity, "곡 목록을 불러오는데에 실패했습니다", Toast.LENGTH_SHORT).show()
    }

}
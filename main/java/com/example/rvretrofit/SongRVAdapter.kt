package com.example.rvretrofit

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SongRVAdapter(val songList: List<SongList>): RecyclerView.Adapter<SongRVAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongRVAdapter.MyViewHolder {
        Log.d("onCreateViewHolder() /", " 메소드 called")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return songList.size
    }

    override fun onBindViewHolder(holder: SongRVAdapter.MyViewHolder, position: Int) {
        Log.d("onBindViewHolder() / ", " 메소드 called / position: $position")

        holder.title.text = songList[position].title
        holder.singer.text = songList[position].singer
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.list_item_title_tv)
        val singer: TextView = itemView.findViewById(R.id.list_item_singer_tv)
    }

}
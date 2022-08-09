package com.example.rvretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rvretrofit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTransactionEvent()
    }

    private fun initTransactionEvent() {
        val songListFragment = SongListFragment()

        // FragmentManager 호출
        supportFragmentManager.beginTransaction().add(R.id.main_fv, songListFragment).commit()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_fv, songListFragment)
        transaction.commit()
    }
}

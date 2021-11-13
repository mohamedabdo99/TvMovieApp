package com.example.movietv.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movietv.databinding.ActivityMainBinding
import com.example.movietv.model.TvShowModel
import com.example.movietv.ui.search.SearchActivity
import com.example.movietv.ui.webview.WebViewActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() , OnItemClick{
    private lateinit var binding: ActivityMainBinding
    private  val viewModel :MainViewModel by viewModels()
    private lateinit var myAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // init RecyclerView
        initRecyclerView()
        // init Clicked
        initClicked()
    }

    private fun initClicked() {
        binding.floatingActionButton.setOnClickListener { v->
            val goTOSearchActivity = Intent(this@MainActivity ,  SearchActivity::class.java)
            startActivity(goTOSearchActivity)
        }
    }

    private fun initRecyclerView() {
        myAdapter = MainAdapter(this@MainActivity)
        binding.recycleViewMain.apply {
            adapter = myAdapter
            layoutManager =
                LinearLayoutManager(this@MainActivity,
                    LinearLayoutManager.VERTICAL,
                    false)
            setHasFixedSize(true)
        }
        viewModel.responseTvShow.observe(this , Observer { list->
            Log.d("res" , " res " + list.get(0))
                myAdapter.tvShows = list
                    })
    }

    override fun OnItemClicked(tvShowModel: TvShowModel) {
        val intent = Intent(
            this@MainActivity, WebViewActivity::class.java
        )
        intent.putExtra("tvShowModel", tvShowModel)
        startActivity(intent)
    }
}



package com.example.movietv.ui.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movietv.R
import com.example.movietv.databinding.ActivitySearchBinding
import com.example.movietv.model.search.TvSearchModel
import com.example.movietv.ui.main.MainAdapter
import com.example.movietv.ui.main.MainViewModel
import com.example.movietv.ui.webview.WebViewActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() , ONITemClickedSearch
{
    private lateinit var binding : ActivitySearchBinding
    private  val viewModel : SearchViewModel by viewModels()
    private lateinit var searchAdapter: SearchAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // init RecyclerView
        initRecyclerView()
        //init Button
        initClick()
    }

    private fun initClick() {
        binding.button.setOnClickListener { v->
            var search : String = binding.etSearch.text.toString()
            viewModel.getAllTvSearch(search)
        }
    }

    private fun initRecyclerView() {
        searchAdapter = SearchAdapter(this@SearchActivity)
        binding.recycleViewSearch.apply {
            adapter = searchAdapter
            layoutManager =
                LinearLayoutManager(this@SearchActivity,
                    LinearLayoutManager.VERTICAL,
                    false)
            setHasFixedSize(true)
        }

        viewModel.responseTvShow.observe(this , Observer { list->
            searchAdapter.tvShows = list
        })

    }

    override fun OnItemClickedSearch(model: TvSearchModel) {
        val intent = Intent(
            this@SearchActivity, WebViewActivity::class.java
        )
        intent.putExtra("tvSearchModel", model)
        startActivity(intent)
    }
}
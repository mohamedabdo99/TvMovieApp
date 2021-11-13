package com.example.movietv.ui.webview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.webkit.WebViewClient
import com.example.movietv.R
import com.example.movietv.databinding.ActivityMainBinding
import com.example.movietv.databinding.ActivityWebViewBinding
import com.example.movietv.model.TvShowModel
import com.example.movietv.model.search.TvSearchModel

class WebViewActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityWebViewBinding
    private lateinit var modelTv : TvShowModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // get Serializable model
        getSerializableModelFromSearch()
    }

    private fun getSerializableModelFromSearch()
    {
        val tvModel = intent.getSerializableExtra("tvSearchModel") as? TvSearchModel
        val Model = intent.getSerializableExtra("tvShowModel") as? TvShowModel

        if (tvModel?.show?.url == null){

            Log.d("model", " model " + Model?.url)
            binding.webView.loadUrl(Model?.url.toString())
            val webSetting = binding.webView.settings
            webSetting.javaScriptEnabled = true
            binding.webView.webViewClient = WebViewClient()
            binding.webView.canGoBack()
        }
        else{
            Log.d("model", " model " + tvModel?.show?.url)
            binding.webView.loadUrl(tvModel?.show?.url.toString())
            val webSetting = binding.webView.settings
            webSetting.javaScriptEnabled = true
            binding.webView.webViewClient = WebViewClient()
            binding.webView.canGoBack()
        }

    }
}



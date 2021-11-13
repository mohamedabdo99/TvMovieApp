package com.example.movietv.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movietv.R
import com.example.movietv.databinding.ActivitySplachBinding
import android.content.Intent
import android.os.Handler

import android.view.WindowManager
import com.example.movietv.ui.main.MainActivity


class SplashActivity : AppCompatActivity()
{
    private lateinit var binding : ActivitySplachBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplachBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //FULL SCREEN
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        //handler
        Handler().postDelayed(Runnable {
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            this@SplashActivity.finish()
        }, 6000)
    }
}
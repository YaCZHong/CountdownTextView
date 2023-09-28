package com.xh.countdowntextview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xh.countdowntextview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnBindEt.setOnClickListener {
                BindEtActivity.actionStart(this@MainActivity)
            }
            btnNoBindEt.setOnClickListener {
                NoBindEtActivity.actionStart(this@MainActivity)
            }
        }
    }
}
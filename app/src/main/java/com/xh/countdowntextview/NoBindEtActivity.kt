package com.xh.countdowntextview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xh.countdowntextview.databinding.ActivityNoBindEtBinding

class NoBindEtActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoBindEtBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoBindEtBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            cdtv.setOnClickListener { cdtv.startCountdown() }
        }
    }

    companion object {
        fun actionStart(context: Context) {
            context.startActivity(Intent(context, NoBindEtActivity::class.java))
        }
    }
}
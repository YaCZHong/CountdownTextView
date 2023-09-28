package com.xh.countdowntextview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xh.countdowntextview.databinding.ActivityBindEtBinding

class BindEtActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBindEtBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBindEtBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            cdtv.bindEt(et)
            cdtv.setOnClickListener {
                // 在这里请求接口，接口请求成功后调用startCountdown()开始倒计时，这里为了演示，就直接调用了
                cdtv.startCountdown()
            }
        }
    }

    companion object {
        fun actionStart(context: Context) {
            context.startActivity(Intent(context, BindEtActivity::class.java))
        }
    }
}
package com.dicoding.azerif

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lifecycleScope.launch {
            delay(3000) // Menunggu selama 3 detik
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            val options = ActivityOptionsCompat.makeCustomAnimation(this@SplashActivity, R.anim.fade_in, R.anim.fade_out)

            startActivity(intent, options.toBundle())
            finish()
        }
    }
}
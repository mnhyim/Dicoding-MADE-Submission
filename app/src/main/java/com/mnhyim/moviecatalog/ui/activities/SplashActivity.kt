package com.mnhyim.moviecatalog.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mnhyim.core.utils.Constants.SPLASH_DURATION
import com.mnhyim.moviecatalog.R
import java.util.*
import kotlin.concurrent.schedule

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Timer().schedule(SPLASH_DURATION) {
            val intent = Intent(this@SplashActivity, CatalogActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
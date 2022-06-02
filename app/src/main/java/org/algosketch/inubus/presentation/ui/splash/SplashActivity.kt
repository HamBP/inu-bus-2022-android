package org.algosketch.inubus.presentation.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import org.algosketch.inubus.BuildConfig.IS_PROD
import org.algosketch.inubus.R
import org.algosketch.inubus.presentation.ui.error.ErrorActivity
import org.algosketch.inubus.presentation.main.MainActivity
import java.time.LocalDateTime

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            val dateTime = LocalDateTime.now()
            val startTime = if (IS_PROD) 6 else 0

            if(dateTime.hour in startTime..23) {
                Intent(this, MainActivity::class.java).apply {
                    startActivity(this)
                }
            } else {
                Intent(this, ErrorActivity::class.java).apply {
                    startActivity(this)
                }
            }
            finish()
        }, 500)
    }
}
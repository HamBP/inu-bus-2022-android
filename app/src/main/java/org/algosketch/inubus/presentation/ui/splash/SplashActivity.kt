package org.algosketch.inubus.presentation.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
            if(dateTime.hour in 0..24) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, ErrorActivity::class.java)
                startActivity(intent)
            }
            finish()
        }, 500)
    }
}
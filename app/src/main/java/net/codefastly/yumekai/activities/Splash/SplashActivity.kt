package net.codefastly.yumekai.activities.Splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import net.codefastly.yumekai.R
import net.codefastly.yumekai.activities.OnBoarding.OnBoardingActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val intent = Intent(this, OnBoardingActivity::class.java)
            startActivity(intent)
        }, 1000)

    }
}
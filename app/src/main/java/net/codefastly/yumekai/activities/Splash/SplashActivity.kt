package net.codefastly.yumekai.activities.Splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.ViewModelProvider
import net.codefastly.yumekai.R
import net.codefastly.yumekai.activities.OnBoarding.OnBoardingActivity
import net.codefastly.yumekai.fragments.Calendar.CalendarViewModel
import net.codefastly.yumekai.fragments.News.NewsViewModel
import net.codefastly.yumekai.fragments.Recent.RecentViewModel
import net.codefastly.yumekai.viewmodels.setCalendarViewModel
import net.codefastly.yumekai.viewmodels.setNewsViewModel
import net.codefastly.yumekai.viewmodels.setRecentsViewModel

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setCalendarViewModel(ViewModelProvider(this).get(CalendarViewModel::class.java))
        setRecentsViewModel(ViewModelProvider(this).get(RecentViewModel::class.java))
        setNewsViewModel(ViewModelProvider(this).get(NewsViewModel::class.java))
        Handler().postDelayed({

            val intent = Intent(this, OnBoardingActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }, 2500)

    }
}
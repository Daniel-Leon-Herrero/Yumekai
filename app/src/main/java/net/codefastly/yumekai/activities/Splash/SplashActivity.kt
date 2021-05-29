package net.codefastly.yumekai.activities.Splash

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import net.codefastly.yumekai.R
import net.codefastly.yumekai.activities.DashboardFullScreen.DashboardFullScreen
import net.codefastly.yumekai.activities.OnBoarding.OnBoardingActivity
import net.codefastly.yumekai.fragments.Calendar.CalendarViewModel
import net.codefastly.yumekai.fragments.News.NewsViewModel
import net.codefastly.yumekai.fragments.Recent.RecentViewModel
import net.codefastly.yumekai.repository.online.repositoryAPI
import net.codefastly.yumekai.utilities.setCalendarViewModel
import net.codefastly.yumekai.utilities.setNewsViewModel
import net.codefastly.yumekai.utilities.setRecentsViewModel
import net.codefastly.yumekai.utilities.setRepoAPI
import java.lang.Exception
import java.net.SocketTimeoutException


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Full Screen Mode
         */
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash)


        val repo = repositoryAPI()
        repo.setContext(this)
        setRepoAPI(repo)
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo

        if (networkInfo != null && networkInfo.isConnected) {
                setCalendarViewModel(ViewModelProvider(this).get(CalendarViewModel::class.java))
                setRecentsViewModel(ViewModelProvider(this).get(RecentViewModel::class.java))
                setNewsViewModel(ViewModelProvider(this).get(NewsViewModel::class.java))

                Handler().postDelayed({
                    val intent = Intent(this, OnBoardingActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                    finish()
                }, 5000)
        } else {
            goToNoInternet()
        }
    }

    fun goToNoInternet(){
        Handler().postDelayed({
            val intent = Intent(this, DashboardFullScreen::class.java).apply {
                this.putExtra( "FULL_SCREEN_TO_LOAD", 1150 )
            }
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }, 1000)
    }

}
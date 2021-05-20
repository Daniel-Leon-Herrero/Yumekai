package net.codefastly.yumekai.activities.DashboardFullScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import net.codefastly.yumekai.R
import net.codefastly.yumekai.fragments.History.HistoryFragment
import net.codefastly.yumekai.fragments.Search.SearchFragment
import kotlin.properties.Delegates

class DashboardFullScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_full_screen)

        val FULL_SCREEN_TO_LOAD =  intent.getIntExtra("FULL_SCREEN_TO_LOAD", 0)
        loadOnFullScreen( FULL_SCREEN_TO_LOAD )



    }

    private fun loadOnFullScreen( btnId: Int ){
        val transaction = supportFragmentManager.beginTransaction()
        when( btnId ){
            R.id.btnSearch -> transaction.replace(R.id.nav_host_fullscreen_fragment, SearchFragment(), "searchFragment")
            R.id.bottom_menu_sheet_btn_historial -> transaction.replace(R.id.nav_host_fullscreen_fragment, HistoryFragment(), "historyFragment")
            else -> finish()
        }
        transaction.commit()
    }

}
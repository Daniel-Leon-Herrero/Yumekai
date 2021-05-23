package net.codefastly.yumekai.activities.DashboardFullScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import net.codefastly.yumekai.R
import net.codefastly.yumekai.fragments.AnimeDetails.AnimeDetailsFragment
import net.codefastly.yumekai.fragments.Calendar.CalendarFragment
import net.codefastly.yumekai.fragments.History.HistoryFragment
import net.codefastly.yumekai.fragments.Search.SearchFragment
import net.codefastly.yumekai.fragments.Shop.Series.SeriesFragment
import net.codefastly.yumekai.fragments.Shop.Categories.CategoriesShopFragment


class DashboardFullScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_full_screen)

        val FULL_SCREEN_TO_LOAD = intent.getIntExtra("FULL_SCREEN_TO_LOAD", 0)
        val ANIME_DETAIL = intent.getIntExtra("ANIME_DETAILS", 0)
        val PREVIOUS_FRAGMENT = intent.getStringExtra("PREVIOUS_FRAGMENT")

        loadOnFullScreen(FULL_SCREEN_TO_LOAD, ANIME_DETAIL, if( PREVIOUS_FRAGMENT.isNullOrEmpty()) "" else PREVIOUS_FRAGMENT )
    }

    private fun loadOnFullScreen(btnId: Int, Anime: Int, previousFragmentTag: String ) {
        val transaction = supportFragmentManager.beginTransaction()
        when (btnId) {
            R.id.btnSearch -> transaction.replace(
                R.id.nav_host_fullscreen_fragment,
                SearchFragment(),
                "searchFragment"
            )
            R.id.bottom_menu_sheet_btn_historial -> transaction.replace(
                R.id.nav_host_fullscreen_fragment,
                HistoryFragment(),
                "historyFragment"
            )
            1122 -> transaction.replace(
                R.id.nav_host_fullscreen_fragment,
                AnimeDetailsFragment(Anime, getPreviousFragment( previousFragmentTag ) ),
                "AnimeDetailsFragment"
            )
            R.id.btnCategoryFilter -> transaction.replace(
                R.id.nav_host_fullscreen_fragment,
                SeriesFragment(),
                "seriesFragment"
            )
            R.id.bottom_menu_sheet_btn_shops -> transaction.replace(
                R.id.nav_host_fullscreen_fragment,
                CategoriesShopFragment(),
                "CategoriesShopFragment"
            )
            else -> finish()
        }
        transaction.commit()
    }

    private fun getPreviousFragment( tag: String ): Fragment? {
        Log.d("TAG", tag)
        return when(tag){
            "calendar" -> null
            "recent" -> null
            else -> SearchFragment()
        }
    }

}
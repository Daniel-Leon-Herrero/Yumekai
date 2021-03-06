package net.codefastly.yumekai.activities.Dashboard

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.navigation.NavGraphNavigator
import androidx.navigation.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar
import net.codefastly.yumekai.R
import net.codefastly.yumekai.activities.DashboardFullScreen.DashboardFullScreen
import net.codefastly.yumekai.databinding.ActivityDashboardBinding
import net.codefastly.yumekai.fragments.Calendar.CalendarFragment
import net.codefastly.yumekai.fragments.News.NewsFragment
import net.codefastly.yumekai.fragments.Recent.RecentFragment

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    private var lastMenuItemId: Int = R.id.menu_item_calendar
    private lateinit var calendarFragment: CalendarFragment
    private lateinit var recentFragment: RecentFragment
    private lateinit var newsFragment: NewsFragment
    private lateinit var currentFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = ActivityDashboardBinding.inflate(layoutInflater)

        /**
         * Full Screen Mode
         */
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(this.binding.root)

        bottomSheetBehavior = BottomSheetBehavior.from(binding.dashboardScreenBottomSheetMenu)


        if (savedInstanceState == null) {
            calendarFragment = CalendarFragment()
            recentFragment = RecentFragment()
            newsFragment = NewsFragment()
            currentFragment = calendarFragment
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.nav_host_fragment,currentFragment)
            transaction.commit()
        }



        binding.bottomMenuSheetBtnHistorial.setOnClickListener { _ ->
            val intent = Intent(this, DashboardFullScreen::class.java).apply {
                this.putExtra( "FULL_SCREEN_TO_LOAD", R.id.bottom_menu_sheet_btn_historial )
            }
            startActivity(intent)
            expandCloseMenuSheet()
        }

        binding.bottomMenuSheetBtnShops.setOnClickListener {
            val intent = Intent(this, DashboardFullScreen::class.java).apply {
                this.putExtra( "FULL_SCREEN_TO_LOAD", R.id.bottom_menu_sheet_btn_shops )
            }
            startActivity(intent)
            expandCloseMenuSheet()
        }

        binding.btnSearch.setOnClickListener { _ ->
            val intent = Intent(this, DashboardFullScreen::class.java).apply {
                this.putExtra( "FULL_SCREEN_TO_LOAD", R.id.btnSearch )
            }
            startActivity(intent)
        }

        binding.bottomMenuSheetBtnBox.setOnClickListener {
            val intent = Intent(this, DashboardFullScreen::class.java).apply {
                this.putExtra( "FULL_SCREEN_TO_LOAD", R.id.bottom_menu_sheet_btn_box )
            }
            startActivity(intent)
        }

        binding.bottomMenuSheetBtnRanking.setOnClickListener {
            val intent = Intent(this,DashboardFullScreen::class.java).apply {
                this.putExtra("FULL_SCREEN_TO_LOAD", R.id.bottom_menu_sheet_btn_ranking)
            }
            startActivity(intent)
        }

        binding.bottomMenuSheetBtnSettings.setOnClickListener {
            val intent = Intent( this, DashboardFullScreen::class.java ).apply {
                this.putExtra("FULL_SCREEN_TO_LOAD", R.id.bottom_menu_sheet_btn_settings)
            }
            startActivity( intent )
        }

        /* TEMPORAL PARA PRUEBAS */
        binding.btnCategoryFilter.setOnClickListener {
            Snackbar.make(it, "Not implemented yet", Snackbar.LENGTH_SHORT).show()
        }

        bottomSheetBehavior.setBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> {
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }

        })


        binding.menu.setOnItemSelectedListener { id ->
            val transaction = supportFragmentManager.beginTransaction()
            when (id) {
                R.id.menu_item_calendar -> {
                    this.lastMenuItemId = id
                    if (calendarFragment.isAdded) {
                        transaction
                            .hide(currentFragment)
                            .show(calendarFragment)
                    } else {
                        transaction
                            .hide(currentFragment)
                            .add(R.id.nav_host_fragment, calendarFragment, "calendarFragment")
                    }
                    currentFragment = calendarFragment
                }
                R.id.menu_item_recents -> {
                    this.lastMenuItemId = id
                    if (recentFragment.isAdded) {
                        transaction
                            .hide(currentFragment)
                            .show(recentFragment)
                    } else {
                        transaction
                            .hide(currentFragment)
                            .add(R.id.nav_host_fragment, recentFragment, "recentFragment")
                    }
                    currentFragment = recentFragment
                }
                R.id.menu_item_news -> {
                    this.lastMenuItemId = id
                    if (newsFragment.isAdded) {
                        transaction
                            .hide(currentFragment)
                            .show(newsFragment)
                    } else {
                        transaction
                            .hide(currentFragment)
                            .add(R.id.nav_host_fragment, newsFragment, "newsFragment")
                    }
                    currentFragment = newsFragment
                }

                R.id.menu_item_drop_menu -> expandCloseMenuSheet()
            }
            transaction.commit()
        }

    }

    override fun onStart() {
        super.onStart()
        binding.menu.setItemSelected(this.lastMenuItemId)
    }

    private fun expandCloseMenuSheet() {
        binding.menu.setItemSelected(this.lastMenuItemId)

        if (bottomSheetBehavior!!.state != BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior!!.state = BottomSheetBehavior.STATE_EXPANDED
        } else {
            bottomSheetBehavior!!.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }



}
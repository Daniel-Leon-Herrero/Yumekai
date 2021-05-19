package net.codefastly.yumekai.activities.Dashboard

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.graphics.drawable.toDrawable
import androidx.navigation.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar
import net.codefastly.yumekai.R
import net.codefastly.yumekai.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDashboardBinding
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    private var lastMenuItemId: Int = 2131296559


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = ActivityDashboardBinding.inflate( layoutInflater )
        setContentView( this.binding.root )

        bottomSheetBehavior = BottomSheetBehavior.from( binding.dashboardScreenBottomSheetMenu )


        binding.bottomMenuSheetBtnHistorial.setOnClickListener { _ ->
            when( binding.menu.getSelectedItemId()){
                R.id.menu_item_calendar -> findNavController(R.id.nav_host_fragment).navigate(R.id.action_calendarFragment_to_historyFragment)
                R.id.menu_item_recents -> findNavController(R.id.nav_host_fragment).navigate(R.id.action_recentFragment_to_historyFragment)
            }

        }

        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when( newState ) {
                    BottomSheetBehavior.STATE_HIDDEN -> {}
                    BottomSheetBehavior.STATE_EXPANDED -> {}
                    BottomSheetBehavior.STATE_COLLAPSED -> {}
                    BottomSheetBehavior.STATE_DRAGGING -> {}
                    BottomSheetBehavior.STATE_SETTLING -> {}
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }

        })



        binding.menu.setOnItemSelectedListener { id ->
            when(id){
                R.id.menu_item_calendar -> {
                    this.lastMenuItemId = id
                    findNavController(R.id.nav_host_fragment).navigate(R.id.calendarFragment)
                }
                R.id.menu_item_recents -> {
                    this.lastMenuItemId = id
                    findNavController(R.id.nav_host_fragment).navigate(R.id.recentFragment)
                }
                R.id.menu_item_news -> {
                    this.lastMenuItemId = id
                    findNavController(R.id.nav_host_fragment).navigate(R.id.newsFragment)
                }
                R.id.menu_item_drop_menu -> expandCloseMenuSheet()
            }
        }

    }

    override fun onStart() {
        super.onStart()
        binding.menu.setItemSelected(R.id.menu_item_calendar)
    }

    private fun expandCloseMenuSheet() {
        binding.menu.setItemSelected( this.lastMenuItemId )

        if( bottomSheetBehavior!!.state != BottomSheetBehavior.STATE_EXPANDED ){
            bottomSheetBehavior!!.state = BottomSheetBehavior.STATE_EXPANDED
        } else {
            bottomSheetBehavior!!.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }
}
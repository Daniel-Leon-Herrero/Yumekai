package net.codefastly.yumekai.activities.Dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import net.codefastly.yumekai.R
import net.codefastly.yumekai.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDashboardBinding
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = ActivityDashboardBinding.inflate( layoutInflater )
        setContentView( this.binding.root )

        bottomSheetBehavior = BottomSheetBehavior.from( binding.dashboardScreenBottomSheetMenu )

        binding.menu.setOnItemSelectedListener { id ->
            when(id){
                R.id.menu_item_calendar -> findNavController(R.id.nav_host_fragment).navigate(R.id.calendarFragment)
                R.id.menu_item_recents -> findNavController(R.id.nav_host_fragment).navigate(R.id.recentFragment)
                R.id.menu_item_news -> findNavController(R.id.nav_host_fragment).navigate(R.id.newsFragment)
                R.id.menu_item_drop_menu -> {

                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        binding.menu.setItemSelected(R.id.menu_item_calendar)
    }
}
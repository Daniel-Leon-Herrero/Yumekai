package net.codefastly.yumekai.activities.Dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController
import net.codefastly.yumekai.R
import net.codefastly.yumekai.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = ActivityDashboardBinding.inflate( layoutInflater )
        setContentView( this.binding.root )

        binding.menu.setOnItemSelectedListener { id ->
            when(id){
                R.id.menu_item_calendar -> findNavController(R.id.nav_host_fragment).navigate(R.id.calendarFragment)
                R.id.menu_item_recents -> findNavController(R.id.nav_host_fragment).navigate(R.id.recentFragment)
                R.id.menu_item_news -> findNavController(R.id.nav_host_fragment).navigate(R.id.newsFragment)
                R.id.menu_item_drop_menu -> Toast.makeText(this,"Menu", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        binding.menu.setItemSelected(R.id.menu_item_calendar)
    }
}
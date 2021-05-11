package net.codefastly.yumekai.activities.Dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.codefastly.yumekai.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = ActivityDashboardBinding.inflate( layoutInflater )
        setContentView( this.binding.root )

    }


}
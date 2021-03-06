package net.codefastly.yumekai.activities.OnBoarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import androidx.viewpager2.widget.ViewPager2
import me.relex.circleindicator.CircleIndicator3
import net.codefastly.yumekai.R
import net.codefastly.yumekai.activities.Dashboard.DashboardActivity
import net.codefastly.yumekai.helpers.ViewPagers.OnBoardingViewPager

class OnBoardingActivity : AppCompatActivity() {

    private val _titles = mutableListOf<String>()
    private val _descriptions = mutableListOf<String>()
    private val _images = mutableListOf<Int>()

    private lateinit var slider: ViewPager2
    private val adapter = OnBoardingViewPager(this._titles, this._descriptions, this._images)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Full Screen Mode
         */
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_on_boarding)


        this.postToList()

        val indicator: CircleIndicator3 = findViewById(R.id.slideIndicator)
        val btnLetGetStarted = findViewById<Button>(R.id.btnLetGetStarted)
        val btnSkip = findViewById<Button>(R.id.btnSkip)

        this.slider = findViewById<ViewPager2>(R.id.onBoardingSlider)
        this.slider.adapter = this.adapter

        indicator.setViewPager(this.slider)

        btnSkip.setOnClickListener  { this.slider.setCurrentItem(3, true) }

        this.slider.registerOnPageChangeCallback( object: ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                btnLetGetStarted.visibility = if( position === 3 ) View.VISIBLE else View.GONE
            }
        })

        btnLetGetStarted.setOnClickListener {

            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

    }

    /**
     *
     */
    private fun addToList( title: String, description: String, image: Int ){
        this._titles.add( title )
        this._descriptions.add( description )
        this._images.add( image )
    }

    /**
     *
     */
    private fun postToList(){
        this.addToList( getString(R.string.first_slide_title), getString(R.string.first_slide_desc), R.drawable.on_boarding_1 )
        this.addToList( getString(R.string.second_slide_title), getString(R.string.second_slide_desc), R.drawable.on_boarding_2 )
        this.addToList( getString(R.string.third_slide_title), getString(R.string.third_slide_desc), R.drawable.on_boarding_3 )
        this.addToList( getString(R.string.fourth_slide_title), getString(R.string.fourth_slide_desc), R.drawable.on_boarding_1 )
    }



}





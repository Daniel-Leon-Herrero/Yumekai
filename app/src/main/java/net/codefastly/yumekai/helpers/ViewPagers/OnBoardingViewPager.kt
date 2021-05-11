package net.codefastly.yumekai.helpers.ViewPagers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import net.codefastly.yumekai.R

class OnBoardingViewPager( private val _titles: List<String>, private val _descriptions: List<String>, private val _images: List<Int>): RecyclerView.Adapter<OnBoardingViewPager.OnBoardingViewHolder>() {

    private var position: Int = 0

    inner class OnBoardingViewHolder( itemView: View ):RecyclerView.ViewHolder( itemView ) {
        val itemImage: ImageView = itemView.findViewById<ImageView>(R.id.imgSlide)
        val itemTitle: TextView = itemView.findViewById<TextView>(R.id.titleSlide)
        val itemDescription: TextView = itemView.findViewById<TextView>(R.id.descriptionSlide)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OnBoardingViewHolder {
        return OnBoardingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.on_boarding_slide, parent, false))
    }

    override fun getItemCount(): Int = if(_titles.isNotEmpty()) _titles.size else 0

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        with(holder){
            itemImage.setImageResource(_images[position])
            itemTitle.text = _titles[position]
            itemTitle.text = _titles[position]
        }
        this.position = position
    }

    fun getPosition(): Int = position

}
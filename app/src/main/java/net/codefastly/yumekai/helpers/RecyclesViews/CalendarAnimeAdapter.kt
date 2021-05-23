package net.codefastly.yumekai.helpers.RecyclesViews

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import net.codefastly.yumekai.R
import net.codefastly.yumekai.activities.Dashboard.DashboardActivity
import net.codefastly.yumekai.activities.DashboardFullScreen.DashboardFullScreen
import net.codefastly.yumekai.models.calendar.AnimeDTO
import java.lang.Exception

class CalendarAnimeAdapter(private val context: Context) :
    RecyclerView.Adapter<CalendarAnimeAdapter.AnimeCalendarViewHolder>() {

    private var dataList = mutableListOf<AnimeDTO>()

    fun setListAnimes(data: MutableList<AnimeDTO>) {
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeCalendarViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_calendar, parent, false)
        return AnimeCalendarViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimeCalendarViewHolder, position: Int) {
        val anime = dataList[position]
        holder.bindView(anime)
    }

    override fun getItemCount(): Int {
        if (dataList.size > 0) {
            return dataList.size
        } else {
            return 0
        }
    }

    inner class AnimeCalendarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(cal: AnimeDTO) {
            if(cal.day.image_url.isNotEmpty()) {
                Picasso.get().load(cal.day.image_url)
                    .into(itemView.findViewById<ImageView>(R.id.calendar_RV_image),
                        object : Callback {
                            override fun onSuccess() {
                            }

                            override fun onError(e: Exception?) {
                                itemView.findViewById<ImageView>(R.id.calendar_RV_image)
                                    .setImageResource(R.drawable.yumekai_failed_portrait)
                            }

                        })
            }else{
                itemView.findViewById<ImageView>(R.id.calendar_RV_image)
                    .setImageResource(R.drawable.yumekai_failed_portrait)
            }
            itemView.findViewById<TextView>(R.id.calendar_categoryTag).text = cal.day.type
            itemView.setOnClickListener {
                val intent =  Intent(context, DashboardFullScreen::class.java).apply {
                    this.putExtra( "FULL_SCREEN_TO_LOAD", 1122 )
                    this.putExtra("ANIME_DETAILS", cal.day.mal_id)
                    this.putExtra("PREVIOUS_FRAGMENT", "calendar" )
                }
                context.startActivity(intent)
            }
        }
    }
}
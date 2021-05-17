package net.codefastly.yumekai.helpers.RecyclesViews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.codefastly.yumekai.R
import net.codefastly.yumekai.fragments.Calendar.CalendarFragmentDirections
import net.codefastly.yumekai.models.calendar.AnimeDTO

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
                    .into(itemView.findViewById<ImageView>(R.id.calendar_RV_image))
            }
            itemView.findViewById<TextView>(R.id.calendar_categoryTag).text = cal.day.type
            itemView.setOnClickListener {
                var action = CalendarFragmentDirections.actionCalendarFragmentToAnimeDetailsFragment(cal)
                it.findNavController().navigate(action)
            }
        }
    }
}
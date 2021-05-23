package net.codefastly.yumekai.helpers.RecyclesViews

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import net.codefastly.yumekai.R
import net.codefastly.yumekai.activities.DashboardFullScreen.DashboardFullScreen
import net.codefastly.yumekai.models.recents.RecentsResponse
import net.codefastly.yumekai.models.recents.Result
import java.lang.Exception

class ItemRecentAnimeAdapter(private val context: Context): RecyclerView.Adapter<ItemRecentAnimeAdapter.ItemRecentViewHolder>() {

    private var dataList: List<Result> = listOf()

    fun setData(data: List<Result>){
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRecentViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_calendar, parent, false)
        return ItemRecentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemRecentViewHolder, position: Int) {
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

    inner class ItemRecentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindView(anime: Result){
            if(anime.image_url.isNotEmpty()) {
                Picasso.get().load(anime.image_url)
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
            itemView.findViewById<TextView>(R.id.calendar_categoryTag).visibility = View.GONE
            itemView.setOnClickListener {
                val intent =  Intent(context, DashboardFullScreen::class.java).apply {
                    this.putExtra( "FULL_SCREEN_TO_LOAD", 1122 )
                    this.putExtra("ANIME_DETAILS", anime.mal_id)
                    /*this.putExtra("PREVIOUS_FRAGMENT", "recent" )*/
                }
                context.startActivity(intent)
            }
        }
    }
}
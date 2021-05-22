package net.codefastly.yumekai.helpers.RecyclesViews

import android.app.Activity
import android.app.ActivityManager
import android.content.ClipDescription
import android.content.Context
import android.content.Intent
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.navigation.ActivityNavigator
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.codefastly.yumekai.R
import net.codefastly.yumekai.activities.DashboardFullScreen.DashboardFullScreen
import net.codefastly.yumekai.models.recents.Result

class SearchAnimeAdapter( private val context: Context):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private val VIEW_TYPE_EMPTY: Int = 0
        private val VIEW_TYPE_SEARCH: Int = 1
    }

    private var dataList : List<Result> = emptyList()

    fun setDataList( data: List<Result> ){
        this.dataList = data
    }

    override fun getItemViewType(position: Int): Int = if( this.dataList.isNullOrEmpty() ) VIEW_TYPE_EMPTY else VIEW_TYPE_SEARCH

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_SEARCH -> SearchViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_calendar, parent, false)
            )
            else -> EmptyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_no_content, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder , position: Int) {
        when( getItemViewType(position)){
            VIEW_TYPE_SEARCH -> {
                val anime = dataList[position]
                SearchViewHolder( holder.itemView ).render( anime )
            }
            else -> EmptyViewHolder( holder.itemView ).render(context.getString(R.string.search_screen_no_content_title), context.getString(R.string.search_screen_no_content_desc))
        }
    }

    override fun getItemCount(): Int = if ( dataList.isNullOrEmpty() ) 1 else dataList.size

    inner class SearchViewHolder( itemView: View ): RecyclerView.ViewHolder( itemView ){
        fun render( anime: Result ){
            if( anime.image_url.isNullOrEmpty() ){
                itemView.findViewById<ImageView>(R.id.calendar_RV_image).setImageResource( R.drawable.yumekai_failed_portrait )
            }else{
                Picasso.get().load( anime.image_url ).into( itemView.findViewById<ImageView>(R.id.calendar_RV_image) )
            }
            itemView.findViewById<TextView>(R.id.calendar_categoryTag).text = anime.type
            itemView.setOnClickListener {
                val intent =  Intent(context, DashboardFullScreen::class.java).apply {
                    this.putExtra( "FULL_SCREEN_TO_LOAD", 1122 )
                    this.putExtra("ANIME_DETAILS", anime.mal_id)

                }
                context.startActivity(intent)
            }
        }
    }

    inner class EmptyViewHolder( itemView: View ): RecyclerView.ViewHolder( itemView ){
        fun render( title: String, description: String ){
            with(itemView){
                findViewById<TextView>(R.id.item_no_content_title).text = title
                findViewById<TextView>(R.id.item_no_content_desc).text = description
            }

        }
    }

}
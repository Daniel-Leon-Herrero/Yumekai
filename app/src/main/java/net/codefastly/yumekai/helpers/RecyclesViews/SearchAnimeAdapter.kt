package net.codefastly.yumekai.helpers.RecyclesViews

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.codefastly.yumekai.R
import net.codefastly.yumekai.models.recents.Result

class SearchAnimeAdapter( private val context: Context):RecyclerView.Adapter<SearchAnimeAdapter.SearchViewHolder>() {

    private var dataList : List<Result> = emptyList()

    fun setDataList( data: List<Result> ){
        this.dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder( LayoutInflater.from(context).inflate(R.layout.item_calendar, parent, false) )
    }

    override fun onBindViewHolder(holder: SearchViewHolder , position: Int) {
        val anime = dataList[position]
        holder.render( anime )
    }

    override fun getItemCount(): Int = if ( dataList.isEmpty() ) 0 else dataList.size

    inner class SearchViewHolder( itemView: View ): RecyclerView.ViewHolder( itemView ){
        fun render( anime: Result ){
            if( anime.image_url.isNullOrEmpty() ){
                itemView.findViewById<ImageView>(R.id.calendar_RV_image).setImageResource( R.drawable.yumekai_failed_portrait )
            }else{
                Picasso.get().load( anime.image_url ).into( itemView.findViewById<ImageView>(R.id.calendar_RV_image) )
            }
            itemView.findViewById<TextView>(R.id.calendar_categoryTag).text = anime.type
        }
    }

}
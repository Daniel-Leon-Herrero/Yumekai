package net.codefastly.yumekai.helpers.RecyclesViews

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import net.codefastly.yumekai.R
import net.codefastly.yumekai.models.room.LocalAnime

class HistoryAnimeAdapter( private val context: Context ): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private val VIEW_TYPE_EMPTY: Int = 0
        private val VIEW_TYPE_HISTORY: Int = 1
    }

    private var dataList: List<LocalAnime> = emptyList()

    fun setData( data: List<LocalAnime> ){
        this.dataList = data
    }

    override fun getItemViewType(position: Int): Int {
        if( this.dataList.size === 0 ){
            return VIEW_TYPE_EMPTY
        }

        return VIEW_TYPE_HISTORY
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when( viewType ) {
            VIEW_TYPE_HISTORY -> HistoryViewHolder( LayoutInflater.from(context).inflate(R.layout.item_historial , parent, false) )
            else -> NoContentViewHolder( LayoutInflater.from(context).inflate(R.layout.item_no_content , parent, false) )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder , position: Int) {
        when( getItemViewType(position) ){
            VIEW_TYPE_HISTORY -> {
                val historyItem: LocalAnime = this.dataList[position]
                HistoryViewHolder( holder.itemView ).render( historyItem )
            }
            else -> NoContentViewHolder( holder.itemView ).render( context.getString(R.string.history_screen_no_content_title), context.getString(R.string.history_screen_no_content_desc )  )
        }
    }

    override fun getItemCount(): Int = if( this.dataList.size === 0 ) 1 else this.dataList.size

    inner class HistoryViewHolder( itemView: View ): RecyclerView.ViewHolder( itemView ){
        fun render( historyItem: LocalAnime ){
            Picasso.get().load(historyItem.imageUrl).into(itemView.findViewById<ImageView>(R.id.history_screen_img))
            itemView.findViewById<TextView>(R.id.history_screen_title).text = historyItem.title
            itemView.findViewById<TextView>(R.id.history_screen_desc).text = historyItem.synopsis
            itemView.findViewById<Button>(R.id.history_screen_btn_finalized).setOnClickListener {
                Snackbar.make(itemView,"Finalized",Snackbar.LENGTH_SHORT).show()
            }
            itemView.findViewById<Button>(R.id.history_screen_btn_recommended).setOnClickListener {
                Snackbar.make(itemView,"Recommended",Snackbar.LENGTH_SHORT).show()
            }
            itemView.findViewById<Button>(R.id.history_screen_btn_following).setOnClickListener {
                Snackbar.make(itemView,"Following",Snackbar.LENGTH_SHORT).show()
            }
            itemView.findViewById<Button>(R.id.history_screen_btn_favourites).setOnClickListener {
                Snackbar.make(itemView,"Favourites",Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    inner class NoContentViewHolder( itemView: View ): RecyclerView.ViewHolder( itemView ){
        fun render( title: String, description: String ){
            itemView.findViewById<TextView>(R.id.item_no_content_title).text = title
            itemView.findViewById<TextView>(R.id.item_no_content_desc).text = description
        }
    }


}
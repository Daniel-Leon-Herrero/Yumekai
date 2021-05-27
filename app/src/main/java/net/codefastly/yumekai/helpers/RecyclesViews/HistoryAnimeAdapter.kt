package net.codefastly.yumekai.helpers.RecyclesViews

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.codefastly.yumekai.R
import net.codefastly.yumekai.activities.DashboardFullScreen.DashboardFullScreen
import net.codefastly.yumekai.helpers.LocalDatabase.LocalAnimeDB
import net.codefastly.yumekai.models.room.LocalAnime
import net.codefastly.yumekai.models.room.LocalAnimeHistory

class HistoryAnimeAdapter(private val context: Context, private val owner: LifecycleOwner) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private val VIEW_TYPE_EMPTY: Int = 0
        private val VIEW_TYPE_HISTORY: Int = 1
    }

    private var dataList: List<LocalAnimeHistory> = emptyList()

    fun setData(data: List<LocalAnimeHistory>) {
        this.dataList = data
    }

    override fun getItemViewType(position: Int): Int {
        if (this.dataList.size === 0) {
            return VIEW_TYPE_EMPTY
        }

        return VIEW_TYPE_HISTORY
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_HISTORY -> HistoryViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_historial, parent, false)
            )
            else -> NoContentViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_no_content, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            VIEW_TYPE_HISTORY -> {
                val historyItem: LocalAnimeHistory = this.dataList[position]
                HistoryViewHolder(holder.itemView).render(historyItem)
            }
            else -> NoContentViewHolder(holder.itemView).render(
                context.getString(R.string.history_screen_no_content_title),
                context.getString(R.string.history_screen_no_content_desc)
            )
        }
    }

    override fun getItemCount(): Int = if (this.dataList.size === 0) 1 else this.dataList.size

    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var anime: LocalAnime
        private var exsist: Boolean = false
        fun render(historyItem: LocalAnimeHistory) {
            Picasso.get().load(historyItem.imageUrl)
                .into(itemView.findViewById<ImageView>(R.id.history_screen_img))
            itemView.findViewById<TextView>(R.id.history_screen_title).text = historyItem.title
            itemView.findViewById<TextView>(R.id.history_screen_desc).text = historyItem.synopsis
            itemView.setOnClickListener {
                val intent = Intent(context, DashboardFullScreen::class.java).apply {
                    this.putExtra("FULL_SCREEN_TO_LOAD", 1122)
                    this.putExtra("ANIME_DETAILS", historyItem.mal_id)
                }
                context.startActivity(intent)
            }

            with(LocalAnimeDB.getLocalAnimeDB(context).localAnime()) {
                CoroutineScope(Dispatchers.IO).launch {
                    if (getIfExsists(historyItem.mal_id)) {
                        var an = getLocalAnimeByMalid(historyItem.mal_id)
                        withContext(Dispatchers.Main) {
                            anime = an
                            checkStatus()
                        }
                        exsist = true
                    } else {
                        anime = LocalAnime(
                            historyItem.mal_id,
                            historyItem.title,
                            historyItem.synopsis,
                            historyItem.imageUrl
                        )
                    }
                }
                itemView.findViewById<Button>(R.id.history_screen_btn_finalized)
                    .setOnClickListener {
                        changeStatus("Finalized")
                    }
                itemView.findViewById<Button>(R.id.history_screen_btn_recommended)
                    .setOnClickListener {
                        changeStatus("Recommended")
                    }
                itemView.findViewById<Button>(R.id.history_screen_btn_following)
                    .setOnClickListener {
                        changeStatus("Following")
                    }
                itemView.findViewById<Button>(R.id.history_screen_btn_favourites)
                    .setOnClickListener {
                        changeStatus("Favourites")
                    }
            }
        }

        private fun changeStatus(stat: String) {
            with(LocalAnimeDB.getLocalAnimeDB(context).localAnime()) {
                when (stat) {
                    "Finalized" -> {
                        anime.finalized = !anime.finalized
                        changeAnimeRoomStatus(anime.finalized, stat)
                        changeButtonStatus(anime.finalized,R.id.history_screen_btn_finalized, "finalized")
                    }
                    "Recommended" -> {
                        anime.recommended = !anime.recommended
                        changeAnimeRoomStatus(anime.recommended, stat)
                        changeButtonStatus(anime.recommended,R.id.history_screen_btn_recommended, "recommended")
                    }
                    "Following" -> {
                        anime.following = !anime.following
                        changeAnimeRoomStatus(anime.following, stat)
                        changeButtonStatus(anime.following,R.id.history_screen_btn_following, "following")
                    }
                    "Favourites" -> {
                        anime.favorite = !anime.favorite
                        changeAnimeRoomStatus(anime.favorite, stat)
                        changeButtonStatus(anime.favorite,R.id.history_screen_btn_favourites, "favourites")
                    }
                }
            }
        }

        private fun changeButtonStatus(item: Boolean,buttonId: Int, action: String) {
            with(itemView.findViewById<Button>(buttonId)) {
                if (item) {
                    text = "Remove to ${action}"
                    background.setTint(resources.getColor(R.color.red_primary_op))
                } else {
                    text = "Add to ${action}"
                    background.setTint(resources.getColor(R.color.alt_black))
                }
            }
        }

        private fun changeAnimeRoomStatus(item: Boolean,stat: String){
            with(LocalAnimeDB.getLocalAnimeDB(context).localAnime()) {
                CoroutineScope(Dispatchers.IO).launch {
                    if (exsist) {
                        updateLocalAnime(anime)
                        if (item) {
                            Snackbar.make(
                                itemView,
                                "${stat} added",
                                Snackbar.LENGTH_SHORT
                            ).show()
                        } else {
                            checkStatus()
                            Snackbar.make(
                                itemView,
                                "${stat} removed",
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }

                    } else {
                        insertLocalAnime(anime)
                        exsist = true
                        Snackbar.make(itemView, "${stat} added", Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }

        fun checkStatus() {
            if(anime.finalized){
                changeButtonStatus(anime.finalized,R.id.history_screen_btn_finalized, "finalized")
            }
            if(anime.recommended){
                changeButtonStatus(anime.recommended,R.id.history_screen_btn_recommended, "recommended")
            }
            if(anime.following){
                changeButtonStatus(anime.following,R.id.history_screen_btn_following, "following")
            }
            if(anime.favorite){
                changeButtonStatus(anime.favorite,R.id.history_screen_btn_favourites, "favourites")
            }
            if (!anime.finalized && !anime.favorite &&
                !anime.recommended && !anime.following
            ) {
                LocalAnimeDB.getLocalAnimeDB(context).localAnime().deleteLocalAnime(anime)
                exsist = false
            }
        }
    }

    inner class NoContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun render(title: String, description: String) {
            itemView.findViewById<TextView>(R.id.item_no_content_title).text = title
            itemView.findViewById<TextView>(R.id.item_no_content_desc).text = description
        }
    }


}
package net.codefastly.yumekai.helpers.ViewPagers

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import net.codefastly.yumekai.R
import net.codefastly.yumekai.helpers.RecyclesViews.CategoryAnimeAdapter
import net.codefastly.yumekai.helpers.RecyclesViews.CharacterAnimeAdapter
import net.codefastly.yumekai.helpers.RecyclesViews.StaffAnimeAdapter
import net.codefastly.yumekai.models.anime.AnimeResponse

class AnimeDetailsViewPager(
    private val context: Context,
    private val tabList: Map<Int, String>,
    private val animeResponse: AnimeResponse,
    private val charactersAdapter: CharacterAnimeAdapter,
    private val staffAnimeAdapter: StaffAnimeAdapter ):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private val GENERAL_VIEW = 0
        private val MEDIA_VIEW = 1
        private val GALERIY_VIEW = 2
        private val COMMENTS_VIEW = 3
    }

    private lateinit var animeCategoriesAdapter: CategoryAnimeAdapter

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {

        return when( viewType ){
            GENERAL_VIEW -> GeneralAnimeDetailsViewHolder( LayoutInflater.from(context).inflate(R.layout.item_anime_details_general, parent, false))
            else -> MediaAnimeDetailsViewHolder( LayoutInflater.from(context).inflate(R.layout.item_anime_details_media, parent, false))
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        when( getItemViewType(position) ){
            GENERAL_VIEW -> {
                GeneralAnimeDetailsViewHolder(holder.itemView).render()

                initGeneralRecyclersView(
                    holder.itemView.findViewById(R.id.item_anime_details_rv_character),
                    holder.itemView.findViewById(R.id.item_anime_details_rv_staff)
                )
            }
            MEDIA_VIEW -> {
                MediaAnimeDetailsViewHolder(holder.itemView).render()
            }
        }
    }

    override fun getItemViewType(position: Int): Int = position


    override fun getItemCount(): Int = tabList.size

    inner class GeneralAnimeDetailsViewHolder( itemView: View ): RecyclerView.ViewHolder( itemView ){
        fun render(){
            var sb: String = ""
            with(itemView) {
                findViewById<TextView>(R.id.item_anime_details_title).text = animeResponse.title
                findViewById<TextView>(R.id.item_anime_details_synopsis).text = animeResponse.synopsis
                findViewById<TextView>(R.id.item_anime_details_type).text = animeResponse.type

                //Pasar  a recycler ( genres )

                animeResponse.licensors.forEachIndexed { index, licensor ->
                    sb += if ( animeResponse.licensors.lastIndex === index ){
                        licensor.name
                    }else{
                        "${licensor.name }, "
                    }
                }
                findViewById<TextView>(R.id.item_anime_details_licenses).text = sb
                findViewById<TextView>(R.id.item_anime_details_source).text = animeResponse.source
                findViewById<TextView>(R.id.item_anime_details_membres).text = animeResponse.members.toString()
                findViewById<TextView>(R.id.item_anime_details_status).text = animeResponse.status
                findViewById<TextView>(R.id.item_anime_details_duration).text = animeResponse.duration
                findViewById<TextView>(R.id.item_anime_details_position).text = "#${animeResponse.rank}"
                findViewById<TextView>(R.id.item_anime_details_english_title).text = animeResponse.title_english
                findViewById<TextView>(R.id.item_anime_details_japanese_title).text = animeResponse.title_japanese
                findViewById<TextView>(R.id.item_anime_details_airing).text = animeResponse.aired.toString()
                findViewById<TextView>(R.id.item_anime_details_released).text = animeResponse.premiered

                sb = ""
                animeResponse.producers.forEachIndexed { index, producer ->
                    sb += if ( animeResponse.producers.lastIndex === index ){
                        producer.name
                    }else{
                        "${producer.name }, "
                    }
                }
                findViewById<TextView>(R.id.item_anime_details_producers).text = sb

                sb = ""
                animeResponse.studios.forEachIndexed { index, studio ->
                    sb += if ( animeResponse.studios.lastIndex === index ){
                        studio.name
                    }else{
                        "${studio.name }, "
                    }
                }
                findViewById<TextView>(R.id.item_anime_details_studios).text = sb
                findViewById<TextView>(R.id.item_anime_details_puntuation).text = "( Puntuated by ${animeResponse.scored_by} users )"
                findViewById<TextView>(R.id.item_anime_details_clasification).text = animeResponse.rating
                findViewById<TextView>(R.id.item_anime_details_popularity).text = animeResponse.popularity.toString()
                findViewById<TextView>(R.id.item_anime_details_favorites).text = animeResponse.favorites.toString()

            }
        }
    }

    inner class MediaAnimeDetailsViewHolder( itemView: View ): RecyclerView.ViewHolder( itemView ){
        fun render(){
            if( !animeResponse.trailer_url.isNullOrEmpty() ){
                val videoId = animeResponse.trailer_url.split("/")[4].split("?")[0]
                Log.e( TAG, "$videoId --> ${animeResponse.trailer_url}")
                with(itemView.findViewById<YouTubePlayerView>(R.id.item_anime_details_media_video)){
                    addYouTubePlayerListener( object: AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            super.onReady(youTubePlayer)
                            youTubePlayer.loadVideo( videoId, 0f )
                        }
                    })
                }
            }

        }
    }

    private fun initGeneralRecyclersView( characterRecyclerView: RecyclerView ,staffRecyclerView: RecyclerView ){
        with(characterRecyclerView){
            itemAnimator = DefaultItemAnimator()
            adapter = charactersAdapter
        }
        with(staffRecyclerView){
            itemAnimator = DefaultItemAnimator()
            adapter = staffAnimeAdapter
        }
    }

}
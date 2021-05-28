package net.codefastly.yumekai.helpers.ViewPagers

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.squareup.picasso.Picasso
import net.codefastly.yumekai.R
import net.codefastly.yumekai.fragments.AnimeDetails.AnimeDetailsViewModel
import net.codefastly.yumekai.helpers.RecyclesViews.*
import net.codefastly.yumekai.models.anime.AnimeResponse
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AnimeDetailsViewPager(
    private val context: Context,
    private val tabList: Map<Int, String>,
    private val animeResponse: AnimeResponse,
    private val charactersAdapter: CharacterAnimeAdapter,
    private val staffAnimeAdapter: StaffAnimeAdapter,
    private val pictureAdapter: PictureAnimeAdapter,
    private val viewModel: AnimeDetailsViewModel
):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private val GENERAL_VIEW = 0
        private val MEDIA_VIEW = 1
        private val GALERIY_VIEW = 2
        private val COMMENTS_VIEW = 3
    }

    private lateinit var animeCategoriesAdapter: CategoryAnimeAdapter
    private var commentsAdapter = CommentsAnimeAdapter(context)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {

        return when( viewType ){
            GENERAL_VIEW -> GeneralAnimeDetailsViewHolder( LayoutInflater.from(context).inflate(R.layout.item_anime_details_general, parent, false))
            MEDIA_VIEW -> MediaAnimeDetailsViewHolder( LayoutInflater.from(context).inflate(R.layout.item_anime_details_media, parent, false))
            GALERIY_VIEW -> GalleryAnimeDetailsViewHolder( LayoutInflater.from(context).inflate(R.layout.item_anime_details_gallery, parent, false))
            else -> CommentsAnimeDetailsViewHolder( LayoutInflater.from(context).inflate(R.layout.item_anime_details_comments, parent, false))
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
            GALERIY_VIEW -> {
                GalleryAnimeDetailsViewHolder(holder.itemView)

                initGalleryRecylerView(
                    holder.itemView.findViewById(R.id.item_anime_details_rv_pictures)
                )
            }
            else -> {
                CommentsAnimeDetailsViewHolder( holder.itemView ).render()

                initCommentsRecyclerView(
                    holder.itemView.findViewById(R.id.item_anime_details_comments_rv)
                )
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

                // Pasar  a recycler ( genres )

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
                findViewById<TextView>(R.id.item_anime_details_airing).text = animeResponse.aired.string
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
        fun render() {
            if( !animeResponse.trailer_url.isNullOrEmpty() ){
                val videoId = animeResponse.trailer_url.split("/")[4].split("?")[0]
                Log.e( TAG, "$videoId --> ${animeResponse.trailer_url}")
                with(itemView.findViewById<YouTubePlayerView>(R.id.item_anime_details_media_video)){
                    val tracker = YouTubePlayerTracker()
                    addYouTubePlayerListener( object: AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            super.onReady(youTubePlayer)
                            youTubePlayer.loadVideo( videoId, 0f )
                            youTubePlayer.addListener( tracker )
                        }

                        override fun onError(
                            youTubePlayer: YouTubePlayer,
                            error: PlayerConstants.PlayerError
                        ) {
                            super.onError(youTubePlayer, error)
                            if( tracker.videoId != videoId ){
                                youTubePlayer.loadVideo( videoId, 0f)
                            }
                        }
                    })

                }

            }

        }
    }

    inner class GalleryAnimeDetailsViewHolder( itemView: View ): RecyclerView.ViewHolder( itemView ){}

    inner class CommentsAnimeDetailsViewHolder( itemView: View ):RecyclerView.ViewHolder( itemView ){
        fun render(){

            if ( !viewModel.comments.value.isNullOrEmpty() ){
                if ( commentsAdapter.itemCount == 0){
                    commentsAdapter.setComments( viewModel.comments.value!! )
                }
            }

            with(itemView){

                Picasso.get().load("https://static-cdn.jtvnw.net/jtv_user_pictures/d0b8ba63-ec93-4e71-b343-b28656e85764-profile_image-300x300.png").into( findViewById<ImageView>(R.id.item_anime_details_comments_user_img) )

                val editText = findViewById<EditText>(R.id.item_anime_details_comments_et)
                editText.setOnEditorActionListener(object: TextView.OnEditorActionListener{
                    override fun onEditorAction(
                        v: TextView?,
                        actionId: Int,
                        event: KeyEvent?
                    ): Boolean {
                        if(actionId == EditorInfo.IME_ACTION_DONE){
                            context.hideKeyboard( itemView )
                            if( !editText.text.isNullOrEmpty() ){
                                postMessageToFirebase( editText.text.toString() )
                            }
                            editText.setText("")
                            return true;
                        }
                        return false;
                    }

                })

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

    private fun initGalleryRecylerView( pictureAnimeRecyclerView: RecyclerView ){
        with( pictureAnimeRecyclerView ){
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            adapter = pictureAdapter
        }
    }

    private fun initCommentsRecyclerView( commentsRecyclerView: RecyclerView ){
        with( commentsRecyclerView ){
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            adapter = commentsAdapter
        }
    }


    private fun postMessageToFirebase(message: String ){
        val currentDateTime = "28/05/2021"

        val isSupporter = (0..1).random()

        val message = hashMapOf<String, Any>(
            "anime_id" to animeResponse.mal_id,
            "message" to message,
            "username" to "KaiiTo01",
            "user_image" to "https://static-cdn.jtvnw.net/jtv_user_pictures/d0b8ba63-ec93-4e71-b343-b28656e85764-profile_image-300x300.png",
            "commented_on" to currentDateTime,
            "supporter" to (isSupporter === 1),
            "reactions" to 0
        )

        viewModel.loadFirebaseComment( message )



    }

    private fun Context.hideKeyboard( view: View ){
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow( view.windowToken, 0)
    }

}
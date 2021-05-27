package net.codefastly.yumekai.helpers.RecyclesViews

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.codefastly.yumekai.R
import net.codefastly.yumekai.models.pictures.Picture

class PictureAnimeAdapter(private val context: Context): RecyclerView.Adapter< PictureAnimeAdapter.PictureAnimeViewHolder>() {

    private var pictureList: List<Picture> = emptyList()


    fun setPictureList( pictureList: List<Picture> ){
        this.pictureList = pictureList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureAnimeViewHolder {
        return PictureAnimeViewHolder( LayoutInflater.from(context).inflate(R.layout.item_anime_picture, parent, false))
    }

    override fun onBindViewHolder(holder: PictureAnimeViewHolder, position: Int) {
        val picture = pictureList[position]
        PictureAnimeViewHolder( holder.itemView ).render( picture )
    }

    override fun getItemCount(): Int = if( pictureList.isNullOrEmpty() ) 0 else pictureList.size

    inner class PictureAnimeViewHolder( itemView: View): RecyclerView.ViewHolder( itemView ){
        fun render( picture: Picture ){
            if( picture.large.isNullOrEmpty() ){
                if ( picture.small.isNullOrEmpty()){
                    itemView.findViewById<ImageView>(R.id.item_anime_picture_img).setImageResource( R.drawable.yumekai_failed_portrait )
                }else{
                    Picasso.get().load( picture.small ).into( itemView.findViewById<ImageView>(R.id.item_anime_picture_img) )
                }
            }else{
                Picasso.get().load( picture.large ).into( itemView.findViewById<ImageView>(R.id.item_anime_picture_img) )
            }
        }
    }

}
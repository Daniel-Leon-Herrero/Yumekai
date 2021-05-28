package net.codefastly.yumekai.helpers.RecyclesViews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.codefastly.yumekai.R
import net.codefastly.yumekai.models.comments.AnimeComment

class CommentsAnimeAdapter( private val context: Context ): RecyclerView.Adapter<CommentsAnimeAdapter.CommentsAnimeViewHolder>() {

    private var dataList: List<AnimeComment> = emptyList()

    fun setComments( commentsList : List<AnimeComment>){
        this.dataList = commentsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommentsAnimeAdapter.CommentsAnimeViewHolder {
        return CommentsAnimeViewHolder( LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false) )
    }

    override fun onBindViewHolder(
        holder: CommentsAnimeAdapter.CommentsAnimeViewHolder,
        position: Int
    ) {
        val comment = dataList[position]
        holder.render( comment )
    }

    override fun getItemCount(): Int = if( dataList.isEmpty() ) 0 else dataList.size

    inner class CommentsAnimeViewHolder( itemView: View ):RecyclerView.ViewHolder( itemView ){
        fun render( comment: AnimeComment){
            with(itemView){
                findViewById<TextView>(R.id.item_comment_username).text = comment.usermame
                findViewById<TextView>(R.id.item_comment_date).text = comment.commented_on
                findViewById<TextView>(R.id.item_comment_reaction_number).text = comment.reactions.toString()
                findViewById<CardView>(R.id.item_comment_supporter_badge).visibility = if( comment.supporter ) View.VISIBLE else View.GONE
                findViewById<TextView>(R.id.item_comment_message).text = comment.message

                if( comment.reactions > 0 ){
                    val color =  ContextCompat.getColor(context, R.color.red_primary)
                    findViewById<TextView>(R.id.item_comment_reaction_number).setTextColor( color )
                    findViewById<ImageView>(R.id.item_comment_reaction_icon).setColorFilter( color )
                }

                if( comment.user_image.isNullOrEmpty() ){
                    findViewById<ImageView>(R.id.item_comment_user_image).setImageResource( R.drawable.yumekai_failed_portrait )
                }else {
                    Picasso.get().load( comment.user_image ).into( findViewById<ImageView>(R.id.item_comment_user_image) )
                }

            }
        }
    }


}
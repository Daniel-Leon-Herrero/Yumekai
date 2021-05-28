package net.codefastly.yumekai.helpers.RecyclesViews

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.codefastly.yumekai.R
import net.codefastly.yumekai.activities.DashboardFullScreen.DashboardFullScreen
import net.codefastly.yumekai.models.news.Item

class NewsAnimeAdapter( private val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var SELECTED_VIEW: Int = R.layout.item_news_text

    private var articlesList: List<Item> = mutableListOf()

    fun setArticles( articles: List<Item> ){
        this.articlesList = articles
    }

    fun setSelectedView( selectedItemLayout: Int ){
        this.SELECTED_VIEW = selectedItemLayout
        notifyItemRangeChanged(0, articlesList.size )
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {

        return when( viewType ){
            R.layout.item_news_text -> TextNewsAnimeViewHolder(LayoutInflater.from(context).inflate(R.layout.item_news_text, parent, false))
            R.layout.item_news_card -> NewsAnimeViewHolder(LayoutInflater.from(context).inflate(R.layout.item_news_card, parent, false))
            else -> NewsAnimeViewHolder(LayoutInflater.from(context).inflate(R.layout.item_news_magazine, parent, false))
        }

    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val article = articlesList[position]
        when( SELECTED_VIEW ){
            R.layout.item_news_text -> TextNewsAnimeViewHolder( holder.itemView ).render( article )
            else -> NewsAnimeViewHolder( holder.itemView ).render( article )
        }
    }

    override fun getItemViewType(position: Int): Int = SELECTED_VIEW

    override fun getItemCount(): Int  = if (articlesList.isNotEmpty()) articlesList.size else 0

    inner class NewsAnimeViewHolder( itemView: View): RecyclerView.ViewHolder( itemView ){
        fun render( article: Item ){
            itemView.findViewById<TextView>(R.id.feed_cardView_title).text = article.title
            itemView.findViewById<TextView>(R.id.feed_cardView_description).text = article.description
            itemView.findViewById<TextView>(R.id.feed_cardView_publisher).text = "${article.author} - ${article.pubDate}"

            if( article.thumbnail.isNotEmpty() ){
                Picasso.get().load( article.thumbnail ).into( itemView.findViewById<ImageView>(R.id.feed_cardView_img) )
                Picasso.get().load( article.thumbnail ).into( itemView.findViewById<ImageView>(R.id.feed_cardView_img) )
            }

            itemView.setOnClickListener {
                readArticleOnWeb( article.link )
            }

        }
    }

    inner class TextNewsAnimeViewHolder( itemView: View): RecyclerView.ViewHolder( itemView ){
        fun render( article: Item ){
            itemView.findViewById<TextView>(R.id.feed_cardView_title).text = article.title
            itemView.findViewById<TextView>(R.id.feed_cardView_description).text = article.description
            itemView.findViewById<TextView>(R.id.feed_cardView_publisher).text = "${article.author} - ${article.pubDate}"

            itemView.setOnClickListener {
                readArticleOnWeb( article.link )
            }
        }
    }

    private fun readArticleOnWeb(webLink: String){
        val intent= Intent(context, DashboardFullScreen::class.java).apply {
            this.putExtra("FULL_SCREEN_TO_LOAD", R.id.news_btn_to_card)
            this.putExtra("WEB_LINK", webLink )
        }
        context.startActivity( intent )
    }

}
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
import net.codefastly.yumekai.models.news.Item

class NewsAnimeAdapter( private val context: Context): RecyclerView.Adapter<NewsAnimeAdapter.NewsAnimeViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsAnimeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_news_card, parent, false)
        return NewsAnimeViewHolder( view )
    }

    private var articlesList: List<Item> = mutableListOf()

    fun setArticles( articles: List<Item> ){
        this.articlesList = articles
    }

    override fun onBindViewHolder(holder: NewsAnimeViewHolder, position: Int) {
        val article = articlesList[position]
        holder.render(article)
    }

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

            /*
            itemView.findViewById<Button>(R.id.feed_cardView_btn).setOnClickListener {
                val intent = Intent(context, RssDetailsActivity::class.java).apply {
                    this.putExtra("WEB_LINK", article.link)
                }
                context.startActivity( intent )
            }
             */

        }

    }
}
package net.codefastly.yumekai.helpers.RecyclesViews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import net.codefastly.yumekai.R
import net.codefastly.yumekai.models.ranking.Top
import java.lang.Exception

class TopAnimeAdapter(private val context: Context) : RecyclerView.Adapter<TopAnimeAdapter.TopAnimeViewHolder>() {

    private var dataList = listOf<Top>()

    fun setData(data: List<Top>){
        dataList = data
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopAnimeAdapter.TopAnimeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_ranking,parent, false)
        return TopAnimeViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopAnimeAdapter.TopAnimeViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        if(dataList.size > 0){
            return dataList.size
        }else{
            return 0
        }
    }

    inner class TopAnimeViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        fun bind(item: Top){
            with(itemView){
                findViewById<TextView>(R.id.item_ranking_rank).text = "#${item.rank.toString()}"
                findViewById<TextView>(R.id.item_ranking_name).text = item.title
                findViewById<TextView>(R.id.item_ranking_score).text = item.score.toString()

                Picasso.get().load(item.image_url).into(findViewById<ImageView>(R.id.item_ranking_image),
                object : Callback{
                    override fun onSuccess() {

                    }

                    override fun onError(e: Exception?) {
                        itemView.findViewById<ImageView>(R.id.calendar_RV_image)
                            .setImageResource(R.drawable.yumekai_failed_portrait)
                    }

                })
            }
        }
    }

}
package net.codefastly.yumekai.helpers.RecyclesViews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.codefastly.yumekai.R
import net.codefastly.yumekai.models.recents.ModelDTO
import net.codefastly.yumekai.models.recents.RecentsResponse
import net.codefastly.yumekai.models.recents.Result

class RecentAdapter(private val context: Context) : RecyclerView.Adapter<RecentAdapter.RecentViewHolder>() {

    private var dataList = listOf<Result>()

    fun setData(data: List<Result>){
        dataList = data
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecentAdapter.RecentViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_recents_populars,parent, false)
        return RecentViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecentAdapter.RecentViewHolder, position: Int) {
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

    inner class RecentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Result){
            with(item){
                with(itemView){
                    if(image_url.isNotEmpty()){
                        Picasso.get().load(image_url).into(findViewById<ImageView>(R.id.item_popular_bg))
                        Picasso.get().load(image_url).into(findViewById<ImageView>(R.id.item_popular_cover))
                    }

                    findViewById<TextView>(R.id.item_popular_title).text = title
                    if(synopsis.isNotEmpty()){
                        findViewById<TextView>(R.id.item_popular_description).text = synopsis
                    }else{
                        findViewById<TextView>(R.id.item_popular_description).text = "Doesn't have synopsis"
                    }
                    findViewById<TextView>(R.id.item_popular_caps).text = episodes.toString()
                    findViewById<TextView>(R.id.item_popular_type).text = type
                }
            }
        }
    }


}
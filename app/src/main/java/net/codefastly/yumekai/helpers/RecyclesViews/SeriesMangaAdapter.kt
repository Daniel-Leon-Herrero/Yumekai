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
import net.codefastly.yumekai.models.shop.SerieShop

class SeriesMangaAdapter( private val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var dataList: List<SerieShop> = emptyList()

    private var lasPositionClicked: Int? = null


    fun setData( data: List<SerieShop> ){
        this.dataList = data
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SerieViewHolder( LayoutInflater.from(context).inflate( R.layout.item_shop_series, parent, false ) )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val serie = this.dataList[position]
        SerieViewHolder( holder.itemView ).render( serie )

        holder.itemView.setOnClickListener {
            this.lasPositionClicked = position
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = if( this.dataList.isNotEmpty() ) this.dataList.size else 0

    inner class SerieViewHolder( itemView: View ): RecyclerView.ViewHolder( itemView ){

        fun render( serie: SerieShop ){
            itemView.findViewById<TextView>(R.id.item_shop_series_title).text = serie.title
            if( serie.image_url.isNotEmpty() ){
                Picasso.get().load(serie.image_url).into( itemView.findViewById<ImageView>(R.id.item_shop_series_img))
            }else {
                itemView.findViewById<ImageView>(R.id.item_shop_series_img).setImageResource(R.drawable.yumekai_failed_portrait)
            }
        }
    }


}
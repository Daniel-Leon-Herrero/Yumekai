package net.codefastly.yumekai.helpers.RecyclesViews

import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import net.codefastly.yumekai.R
import net.codefastly.yumekai.fragments.Shop.Series.SeriesViewModel
import net.codefastly.yumekai.models.shop.SerieShop

class SeriesMangaAdapter( private val context: Context, private val viewModel: SeriesViewModel): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var dataList: List<SerieShop> = emptyList()
    private var SELECTED_ITEM: Int = 0


    fun setData( data: List<SerieShop> ){
        this.dataList = data
    }

    private fun setSelectedSerie( position: Int ){
        val lastPosition = SELECTED_ITEM
        SELECTED_ITEM = position
        notifyItemChanged(lastPosition)
        notifyItemChanged(SELECTED_ITEM)
    }

    override fun getItemId(position: Int): Long = position as Long

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            SELECTED_ITEM -> ActiveSerieViewHolder( LayoutInflater.from(context).inflate(R.layout.item_shop_series_active, parent, false))
            else -> SerieViewHolder( LayoutInflater.from(context).inflate( R.layout.item_shop_series, parent, false ) )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val serie = this.dataList[position]
        when(position){
            SELECTED_ITEM -> ActiveSerieViewHolder( holder.itemView ).render( serie )
            else -> SerieViewHolder( holder.itemView ).render( serie, position )
        }

    }

    override fun getItemViewType(position: Int): Int = position

    override fun getItemCount(): Int = if( this.dataList.isNotEmpty() ) this.dataList.size else 0

    inner class SerieViewHolder( itemView: View ): RecyclerView.ViewHolder( itemView ){

        fun render( serie: SerieShop, position: Int ){
            itemView.findViewById<TextView>(R.id.item_shop_series_title).text = serie.title
            if( serie.image_url.isNotEmpty() ){
                Picasso.get().load(serie.image_url).into( itemView.findViewById<ImageView>(R.id.item_shop_series_img))
            }else {
                itemView.findViewById<ImageView>(R.id.item_shop_series_img).setImageResource(R.drawable.yumekai_failed_portrait)
            }

            itemView.setOnClickListener { view ->
                Snackbar.make(view, "Serie seleccionada: ${ serie.title }", Snackbar.LENGTH_SHORT).show()
                setSelectedSerie(position)
                if( serie.title.isNotEmpty() ){
                    viewModel.getVolumesBySerie(serie.title)
                }
            }
        }
    }

    inner class ActiveSerieViewHolder( itemView: View ): RecyclerView.ViewHolder( itemView ){

        fun render( serie: SerieShop ){
            itemView.findViewById<TextView>(R.id.item_shop_series_active_title).text = serie.title
            if( serie.image_url.isNotEmpty() ){
                Log.e(TAG, serie.image_url)
                Picasso.get().load(serie.image_url).into( itemView.findViewById<ImageView>(R.id.item_shop_series_active_img))
            }else {
                itemView.findViewById<ImageView>(R.id.item_shop_series_active_img).setImageResource(R.drawable.yumekai_failed_portrait)
            }
        }
    }


}
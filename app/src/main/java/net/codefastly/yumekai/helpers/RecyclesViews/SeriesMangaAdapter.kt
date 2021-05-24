package net.codefastly.yumekai.helpers.RecyclesViews

import android.content.Context
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

class SeriesMangaAdapter( private val context: Context, private val viewModel: SeriesViewModel): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var dataList: List<SerieShop> = emptyList()



    fun setData( data: List<SerieShop> ){
        this.dataList = data
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SerieViewHolder( LayoutInflater.from(context).inflate( R.layout.item_shop_series, parent, false ) )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val serie = this.dataList[position]
        SerieViewHolder( holder.itemView ).render( serie )

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

            itemView.setOnClickListener { view ->
                Snackbar.make(view, "Serie seleccionada: ${ serie.title }", Snackbar.LENGTH_SHORT).show()

                if ( serie.title.contains("Hero") ){
                    viewModel.getVolumesBySerie("Magi")
                }else{
                    viewModel.getVolumesBySerie(serie.title)
                }
            }
        }
    }


}
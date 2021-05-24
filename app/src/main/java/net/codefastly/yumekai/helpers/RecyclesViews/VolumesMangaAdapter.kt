package net.codefastly.yumekai.helpers.RecyclesViews

import android.content.Context
import android.graphics.Paint
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import net.codefastly.yumekai.R
import net.codefastly.yumekai.models.shop.VolumeShop

class VolumesMangaAdapter( private val context: Context):RecyclerView.Adapter<VolumesMangaAdapter.VolumesMangaViewHolder>() {

    private var dataList= mutableListOf<VolumeShop>()

    fun setData( data: MutableList<VolumeShop> ){
        data.sortBy { it.volume }
        this.dataList = data
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VolumesMangaAdapter.VolumesMangaViewHolder = VolumesMangaViewHolder( LayoutInflater.from(context).inflate( R.layout.item_shop_volume, parent, false) )

    override fun onBindViewHolder(
        holder: VolumesMangaAdapter.VolumesMangaViewHolder,
        position: Int
    ) {
        val volume = this.dataList[ position ]
        holder.render( volume )
    }

    override fun getItemCount(): Int = if( dataList.isNotEmpty() ) this.dataList.size else 0

    inner class VolumesMangaViewHolder( itemView: View ): RecyclerView.ViewHolder( itemView ){
        fun render( volume: VolumeShop ){
            if ( volume.image_url.isNullOrEmpty() ){
                itemView.findViewById<ImageView>(R.id.item_shop_volume_image).setImageResource(R.drawable.magi_manga_vol1)
            }else{
                Picasso.get().load( volume.image_url).into(itemView.findViewById<ImageView>(R.id.item_shop_volume_image))
            }
            with(itemView){
                findViewById<TextView>(R.id.item_shop_volume_title).text = volume.title
                with(findViewById<TextView>(R.id.item_shop_volume_rtl_price)){
                    text = "$" + volume.price_rtl.toString()
                    paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                }
                findViewById<TextView>(R.id.item_shop_volume_price).text = "$" + volume.price.toString()
                findViewById<TextView>(R.id.item_shop_volume_vol).text = "Volume ${volume.volume}"

                setOnClickListener {
                    Snackbar.make( it , "Volumen seleccionado: ${volume.title} ( ${volume.volume} )", Snackbar.LENGTH_SHORT).show()

                }
            }
        }
    }


}
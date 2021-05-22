package net.codefastly.yumekai.helpers.RecyclesViews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.codefastly.yumekai.R
import net.codefastly.yumekai.models.shop.Volume

class VolumesMangaAdapter( private val context: Context):RecyclerView.Adapter<VolumesMangaAdapter.VolumesMangaViewHolder>() {

    private var dataList: List<Volume> = emptyList()

    fun setData( data: List<Volume> ){
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

    override fun getItemCount(): Int = if( dataList.isNotEmpty() ) this.dataList.size else 1

    inner class VolumesMangaViewHolder( itemView: View ): RecyclerView.ViewHolder( itemView ){
        fun render( volume: Volume ){
            with(itemView){
                findViewById<TextView>(R.id.item_shop_volume_title).text = volume.title
                findViewById<ImageView>(R.id.item_shop_volume_image).setImageResource(R.drawable.magi_manga_vol1)
                findViewById<TextView>(R.id.item_shop_volume_rtl_price).text = volume.rtl_price.toString()
                findViewById<TextView>(R.id.item_shop_volume_price).text = volume.price.toString()
                findViewById<TextView>(R.id.item_shop_volume_vol).text = "Volume ${volume.volume}"
            }
        }
    }


}
package net.codefastly.yumekai.helpers.ViewPagers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.codefastly.yumekai.R

class DrawerViewPager(
    private val itemList: List<String>
): RecyclerView.Adapter<DrawerViewPager.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder( LayoutInflater.from(parent.context).inflate(R.layout.item_drawer, parent, false) )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.render( itemList[position] )
    }

    override fun getItemCount(): Int = if( itemList.isEmpty() ) 0 else itemList.size

    inner class ItemViewHolder( itemView: View):RecyclerView.ViewHolder( itemView ){
        fun render( item: String ){
            itemView.findViewById<TextView>(R.id.item_drawer_title).text = item
        }
    }

}
package net.codefastly.yumekai.helpers.ViewPagers

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.codefastly.yumekai.R
import net.codefastly.yumekai.helpers.RecyclesViews.LocalAnimeAdapter
import net.codefastly.yumekai.models.room.LocalAnime

class DrawerViewPager(
    private val itemList: List< List<LocalAnime> >,
    private val context: Context
): RecyclerView.Adapter<DrawerViewPager.ItemViewHolder>() {

    private lateinit var localAnimeAdapter : LocalAnimeAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder( LayoutInflater.from(parent.context).inflate(R.layout.pages_drawer, parent, false) )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        initRecyclerView( holder.itemView.findViewById(R.id.pager_drawer_rv), itemList[position] )
    }

    override fun getItemCount(): Int = if( itemList.isEmpty() ) 0 else itemList.size

    inner class ItemViewHolder( itemView: View):RecyclerView.ViewHolder( itemView ){ }

    private fun initRecyclerView( recyclerView: RecyclerView, animeList: List<LocalAnime> ){
        localAnimeAdapter = LocalAnimeAdapter(context)
        with(recyclerView){
            itemAnimator = DefaultItemAnimator()
            adapter = localAnimeAdapter
        }
        localAnimeAdapter.setData( animeList )
    }

}
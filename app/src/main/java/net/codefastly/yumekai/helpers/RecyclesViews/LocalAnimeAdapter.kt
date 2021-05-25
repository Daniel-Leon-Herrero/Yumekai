package net.codefastly.yumekai.helpers.RecyclesViews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.codefastly.yumekai.R
import net.codefastly.yumekai.fragments.AnimeDetails.AnimeDetailsFragment
import net.codefastly.yumekai.fragments.Drawer.DrawerFragment
import net.codefastly.yumekai.models.room.LocalAnime

class LocalAnimeAdapter( private  val context: Context, private val currentFragment: DrawerFragment ): RecyclerView.Adapter<LocalAnimeAdapter.LocalAnimeViewHolder>() {

    private var dataList = emptyList<LocalAnime>()

    fun setData( data: List<LocalAnime> ){
        this.dataList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocalAnimeViewHolder {
        return LocalAnimeViewHolder( LayoutInflater.from(context).inflate(R.layout.item_drawer, parent, false))
    }

    override fun onBindViewHolder(holder: LocalAnimeViewHolder, position: Int) {
        val animeItem = dataList[position]
        holder.render( animeItem )
    }

    override fun getItemCount(): Int = if( dataList.isEmpty() ) 0 else dataList.size

    inner class LocalAnimeViewHolder( itemView: View): RecyclerView.ViewHolder(itemView) {
        fun render( animeItem: LocalAnime ){
            with(itemView){
                findViewById<TextView>(R.id.item_drawer_title).text = animeItem.title
                findViewById<TextView>(R.id.item_drawer_synopsis).text = animeItem.synopsis

                if( animeItem.imageUrl.isNotEmpty() ){
                    Picasso.get().load( animeItem.imageUrl ).into( findViewById<ImageView>(R.id.item_drawer_img) )
                }else{
                    findViewById<ImageView>(R.id.item_drawer_img).setImageResource(R.drawable.yumekai_failed_portrait)
                }

                setOnClickListener {
                    val mContext = context as FragmentActivity
                    val transaction = mContext.supportFragmentManager.beginTransaction()
                    transaction.hide( currentFragment ).add( R.id.nav_host_fullscreen_fragment, AnimeDetailsFragment(animeItem.mal_id, currentFragment ) )
                    transaction.commit()
                }
            }
        }
    }


}
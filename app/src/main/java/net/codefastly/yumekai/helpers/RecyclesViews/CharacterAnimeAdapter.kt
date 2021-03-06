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
import net.codefastly.yumekai.models.AnimeCharacters.Character
import java.lang.Exception

class CharacterAnimeAdapter(private val context: Context) :
    RecyclerView.Adapter<CharacterAnimeAdapter.StaffAnimeViewHolder>() {

    private var dataList = listOf<Character>()

    fun setListCharacter(data: List<Character>){
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StaffAnimeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_anim_character_staff, parent, false)
        return StaffAnimeViewHolder(view)
    }

    override fun onBindViewHolder(holder: StaffAnimeViewHolder, position: Int) {
        val anime = dataList[position]
        holder.bindView(anime)
    }

    override fun getItemCount(): Int {
        if(dataList.size > 0){
            return dataList.size
        }else {
            return 0
        }
    }


    inner class StaffAnimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(char: Character) {
            if (char.image_url.isNotEmpty() && !char.image_url.contains("questionmark")) {
                Picasso.get().load(char.image_url)
                    .into(itemView.findViewById<ImageView>(R.id.animeDetail_item_image),
                        object : Callback {
                            override fun onSuccess() {
                            }

                            override fun onError(e: Exception?) {
                                itemView.findViewById<ImageView>(R.id.animeDetail_item_image)
                                    .setImageResource(R.drawable.yumekai_unknown_portrait)
                            }

                        })
            } else {
                itemView.findViewById<ImageView>(R.id.animeDetail_item_image)
                    .setImageResource(R.drawable.yumekai_unknown_portrait)
            }
            itemView.findViewById<TextView>(R.id.animeDetail_item_name).text = char.name

        }
    }


}
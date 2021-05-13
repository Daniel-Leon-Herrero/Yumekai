package net.codefastly.yumekai.helpers.RecyclesViews

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.codefastly.yumekai.R


class CalenderAnimeAdapter(private val context: Context) :
    RecyclerView.Adapter<CalenderAnimeAdapter.AnimesViewHolder>() {

    private var dataList = mutableListOf<String>()

    fun setListImagesAnimes(data: MutableList<String>) {
        dataList = data
        Log.d("DataList", dataList.toString())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimesViewHolder {
        Log.d("Ada", "Ada")
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_calendar, parent, false)
        return AnimesViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimesViewHolder, position: Int) {
        val tend = dataList[position]
        holder.bindView(tend)
    }

    override fun getItemCount(): Int {
        if (dataList.size > 0) {
            return dataList.size
        } else {
            return 0
        }
    }

    inner class AnimesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(images: String) {
            Picasso.get().load(images).into(itemView.findViewById<ImageView>(R.id.calendar_RV_image))
            itemView.setOnClickListener {

            }
        }
    }
}

package net.codefastly.yumekai.helpers.RecyclesViews

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.codefastly.yumekai.R

class CalendarAnimeAdapter(private val context: Context) :
    RecyclerView.Adapter<CalendarAnimeAdapter.TendenciaViewHolder>() {

    private var dataList = mutableListOf<String>()

    fun setListAnimes(data: MutableList<String>) {
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TendenciaViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_calendar, parent, false)
        return TendenciaViewHolder(view)
    }

    override fun onBindViewHolder(holder: TendenciaViewHolder, position: Int) {
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

    inner class TendenciaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(image: String) {
            Picasso.get().load(image).into(itemView.findViewById<ImageView>(R.id.calendar_RV_image))
            itemView.setOnClickListener {
                Log.d("Hola", "Hola")
            }
        }
    }
}
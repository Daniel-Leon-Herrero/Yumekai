package net.codefastly.yumekai.fragments.Shop.Series

import android.content.ContentValues.TAG
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.codefastly.yumekai.models.shop.SerieShop
import net.codefastly.yumekai.models.shop.Volume
import net.codefastly.yumekai.models.shop.VolumeShop
import net.codefastly.yumekai.repository.online.RepositoryFirebase

class SeriesViewModel: ViewModel() {

    private lateinit var _owner: LifecycleOwner

    private val repositoryFirebase: RepositoryFirebase = RepositoryFirebase()

    var fetching: Boolean = false

    private val _volumes = MutableLiveData<MutableList<VolumeShop>>()
    val volumes : LiveData<MutableList<VolumeShop>> get() = _volumes

    private val _series = MutableLiveData<List<SerieShop>>()
    val series : LiveData<List<SerieShop>> get() = _series


    fun attach( fragment: SeriesFragment ){
        this._owner = fragment
    }

    fun getAllSeries(){
        this._series.value = this.generateSeriesList()
    }

    fun getVolumesBySerie( serie: String ){
        fetching = true
        this.repositoryFirebase.getVolumesBySerie( serie ).observe( _owner, { volumeList ->
            fetching = false
            this._volumes.value = volumeList
        })
    }


    private fun generateSeriesList(): List<SerieShop> {
        val pr1: SerieShop = SerieShop("My Hero Academia", "https://static.posters.cz/image/750/posters/my-hero-academia-cobalt-blast-group-i63331.jpg", listOf("manga","anime","movie","ova"))
        val pr2: SerieShop = SerieShop("Demon Slayer", "https://allgamersin.com/wp-content/uploads/2020/03/Demon-Slayer.jpg", listOf("manga","anime","movie"))
        val pr3: SerieShop = SerieShop("Jujutsu Kaisen", "https://cdn.hobbyconsolas.com/sites/navi.axelspringer.es/public/media/image/2021/02/jujutsu-kaisen-2240475.jpg", listOf("manga"))
        val pr4: SerieShop = SerieShop("Promised Neverland", "https://images-na.ssl-images-amazon.com/images/I/91YiYAMX-ZL.jpg", listOf("manga", "anime"))
        val pr5: SerieShop = SerieShop("Toilet-Bound Hanako-Kun", "https://static.posters.cz/image/750/posters/my-hero-academia-cobalt-blast-group-i63331.jpg", listOf("manga"))
        val pr6: SerieShop = SerieShop("Naruto Shippuden", "https://static.posters.cz/image/750/posters/my-hero-academia-cobalt-blast-group-i63331.jpg", listOf("manga"))
        val pr7: SerieShop = SerieShop("Death Note", "https://static.posters.cz/image/750/posters/my-hero-academia-cobalt-blast-group-i63331.jpg", listOf("manga"))

        return listOf<SerieShop>(pr1, pr2, pr3, pr4, pr5, pr6, pr7)
    }

}
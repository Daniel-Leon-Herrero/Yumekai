package net.codefastly.yumekai.fragments.Shop.Series

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.codefastly.yumekai.models.shop.SerieShop
import net.codefastly.yumekai.models.shop.Volume
import net.codefastly.yumekai.repository.online.RepositoryFirebase

class SeriesViewModel: ViewModel() {

    private val repositoryFirebase: RepositoryFirebase = RepositoryFirebase()

    private val _volumes = MutableLiveData<List<Volume>>()
    val volumes : LiveData<List<Volume>> get() = _volumes

    private val _series = MutableLiveData<List<SerieShop>>()
    val series : LiveData<List<SerieShop>> get() = _series

    fun getAllVolumes(category: String ){
        this._volumes.value = this.generateVolumeList()
        this._series.value = this.generateSeriesList()
    }

    init {
        val repositoryFirebase: RepositoryFirebase = RepositoryFirebase()
        this.repositoryFirebase.getVolumesBySerie("Magi")
    }

    private fun generateVolumeList(): List<Volume> {
        val pr1: Volume = Volume("Magi Manga", 1,"https://firebasestorage.googleapis.com/v0/b/yumekai-app.appspot.com/o/manga%2Fmagi_manga_vol1.png?alt=media&token=5396496d-02a5-411f-b841-fcdab9e39aa9",11.99f, 9.99f)
        val pr2: Volume = Volume("Magi Manga", 2,"https://firebasestorage.googleapis.com/v0/b/yumekai-app.appspot.com/o/manga%2Fmagi_manga_vol2.png?alt=media&token=e917f187-4d50-43d1-ab41-c2a12b44939d",11.99f, 9.99f)
        val pr3: Volume = Volume("Magi Manga", 3,"https://firebasestorage.googleapis.com/v0/b/yumekai-app.appspot.com/o/manga%2Fmagi_manga_vol3.png?alt=media&token=f8554efe-82cd-4cb0-ae5e-f73c9edfb1ef",11.99f, 9.99f)
        val pr4: Volume = Volume("Magi Manga", 4,"https://firebasestorage.googleapis.com/v0/b/yumekai-app.appspot.com/o/manga%2Fmagi_manga_vol4.png?alt=media&token=0096c678-3783-4be9-8a26-da86ab1d19d3",11.99f, 9.99f)
        val pr5: Volume = Volume("Magi Manga", 5,"https://firebasestorage.googleapis.com/v0/b/yumekai-app.appspot.com/o/manga%2Fmagi_manga_vol5.png?alt=media&token=a7c2ba59-baa8-4e2f-af0e-9d78c61649a7",11.99f, 9.99f)
        val pr6: Volume = Volume("Magi Manga", 6,"https://firebasestorage.googleapis.com/v0/b/yumekai-app.appspot.com/o/manga%2Fmagi_manga_vol6.png?alt=media&token=335d534a-186e-4ec1-97de-858aa9bdd275",11.99f, 9.99f)

        val lista = listOf<Volume>(pr1, pr2, pr3, pr4, pr5, pr6)

        return lista
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
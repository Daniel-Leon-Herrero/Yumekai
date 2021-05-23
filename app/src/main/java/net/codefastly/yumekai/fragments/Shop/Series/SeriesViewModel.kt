package net.codefastly.yumekai.fragments.Shop.Series

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.codefastly.yumekai.models.shop.SerieShop
import net.codefastly.yumekai.models.shop.Volume

class SeriesViewModel: ViewModel() {

    private val _volumes = MutableLiveData<List<Volume>>()
    val volumes : LiveData<List<Volume>> get() = _volumes

    private val _series = MutableLiveData<List<SerieShop>>()
    val series : LiveData<List<SerieShop>> get() = _series

    fun getAllVolumes( category: String ){
        this._volumes.value = this.generateVolumeList()
        this._series.value = this.generateSeriesList()
    }

    private fun generateVolumeList(): List<Volume> {
        val pr1: Volume = Volume("Magi Manga", 1, 11.99f, 9.99f)
        val pr2: Volume = Volume("Magi Manga", 2, 11.99f, 9.99f)
        val pr3: Volume = Volume("Magi Manga", 3, 11.99f, 9.99f)
        val pr4: Volume = Volume("Magi Manga", 4, 11.99f, 9.99f)
        val pr5: Volume = Volume("Magi Manga", 5, 11.99f, 9.99f)
        val pr6: Volume = Volume("Magi Manga", 6, 11.99f, 9.99f)


        return listOf<Volume>(pr1, pr2, pr3, pr4, pr5, pr6)
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
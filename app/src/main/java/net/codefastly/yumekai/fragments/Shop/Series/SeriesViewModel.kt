package net.codefastly.yumekai.fragments.Shop.Series

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.codefastly.yumekai.models.shop.Volume

class SeriesViewModel: ViewModel() {

    private val _volumes = MutableLiveData<List<Volume>>()
    val volumes : LiveData<List<Volume>> get() = _volumes


    fun getAllVolumes( category: String ){
        val pr1: Volume = Volume("Magi Manga", 1, 11.99f, 9.99f)
        val pr2: Volume = Volume("Magi Manga", 2, 11.99f, 9.99f)
        val pr3: Volume = Volume("Magi Manga", 3, 11.99f, 9.99f)
        val pr4: Volume = Volume("Magi Manga", 4, 11.99f, 9.99f)
        val pr5: Volume = Volume("Magi Manga", 5, 11.99f, 9.99f)
        val pr6: Volume = Volume("Magi Manga", 6, 11.99f, 9.99f)

        val listPr = listOf<Volume>(pr1, pr2, pr3, pr4, pr5, pr6)
        this._volumes.value = listPr
    }

}
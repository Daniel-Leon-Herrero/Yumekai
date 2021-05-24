package net.codefastly.yumekai.fragments.Shop.Series

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.codefastly.yumekai.models.shop.SerieShop
import net.codefastly.yumekai.models.shop.VolumeShop
import net.codefastly.yumekai.repository.online.RepositoryFirebase

class SeriesViewModel: ViewModel() {

    private lateinit var _owner: LifecycleOwner

    private val repositoryFirebase: RepositoryFirebase = RepositoryFirebase()

    private val _fetching = MutableLiveData<Boolean>()
    val fetching : LiveData<Boolean> get() = _fetching

    private val _volumes = MutableLiveData<MutableList<VolumeShop>>()
    val volumes : LiveData<MutableList<VolumeShop>> get() = _volumes

    private val _series = MutableLiveData<List<SerieShop>>()
    val series : LiveData<List<SerieShop>> get() = _series


    fun attach( fragment: SeriesFragment ){
        this._owner = fragment
    }

    fun getAllAvailableSeries(){
        this.repositoryFirebase.getAvailableSeries().observe( _owner, { serieList ->
            this._series.value = serieList
        })
    }

    fun getVolumesBySerie( serie: String ){
        this._fetching.value = true
        this.repositoryFirebase.getVolumesBySerie( serie ).observe( _owner, { volumeList ->
            this._fetching.value = false
            this._volumes.value = volumeList
        })
    }


}
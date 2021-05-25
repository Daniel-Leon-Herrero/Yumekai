package net.codefastly.yumekai.fragments.Drawer

import androidx.lifecycle.ViewModel
import net.codefastly.yumekai.R
import net.codefastly.yumekai.models.room.LocalAnime

class DrawerViewModel: ViewModel() {

    val tabList = listOf<String>(
        "Finalized",
        "Recommended",
        "Following",
        "Favorites"
    )

    val anime = LocalAnime(
        1,
        "Prueba",
        "Desc",
        ""
    )

    val list = listOf(
        listOf<LocalAnime>(anime, anime, anime, anime, anime, anime),
        listOf<LocalAnime>(anime, anime, anime),
        listOf<LocalAnime>(anime,  anime),
        listOf<LocalAnime>(anime, anime, anime, anime)
    )

}
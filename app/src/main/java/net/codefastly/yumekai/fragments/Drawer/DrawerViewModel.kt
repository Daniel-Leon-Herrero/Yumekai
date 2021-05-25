package net.codefastly.yumekai.fragments.Drawer

import androidx.lifecycle.ViewModel
import net.codefastly.yumekai.R

class DrawerViewModel: ViewModel() {

    val list = listOf(
        "Favoritos",
        "Recomendados",
        "Siguiendo",
        "Finalizados"
    )

    val icons = listOf(
        R.drawable.ic_baseline_local_fire_department_36,
        R.drawable.ic_baseline_playlist_add_check_36,
        R.drawable.ic_baseline_category_36,
        R.drawable.ic_baseline_menu_book_36,
    )


}
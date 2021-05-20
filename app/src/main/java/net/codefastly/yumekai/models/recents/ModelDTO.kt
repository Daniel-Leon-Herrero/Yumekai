package net.codefastly.yumekai.models.recents

import androidx.lifecycle.MutableLiveData

class ModelDTO(
    var idCategory: Int,
    var category: String,
    var detail: String,
    var icon: Int,
    var iconTint: Int,
    var textButton: String,
    var itemList: RecentsResponse
)
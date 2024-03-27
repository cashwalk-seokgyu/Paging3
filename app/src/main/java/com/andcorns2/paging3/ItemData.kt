package com.andcorns2.paging3

data class ItemData(
    val title: Int = 0
)

var res = mutableListOf<ItemData>()

fun getList(page: Int): List<ItemData> {
    val list = mutableListOf<ItemData>()
    for (i in page * 25 until (page * 25) + 25) {
        if (i < res.size) list.add(res[i])
    }
    return list
}

fun setList() {
    res = mutableListOf()
    for(i in 1..1000) {
        res.add(ItemData(title = i))
    }
}

fun deleteItem(idx: Int) {
    res.remove(ItemData(idx))
}
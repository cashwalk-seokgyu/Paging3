package com.andcorns2.paging3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    var list: Flow<PagingData<ItemData>> = flowOf(PagingData.empty())

    val itemDeleteObserve: MutableLiveData<Unit> = MutableLiveData()

    init {
        setList()
        getContent()
    }

    private fun getContent() {

        viewModelScope.launch {
            list = ItemRepository().getSamplePagingSource()
        }
    }

    fun setItemDelete(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            itemDeleteObserve.postValue(deleteItem(id))
        }
    }
}
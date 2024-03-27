package com.andcorns2.paging3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private var _list = MutableLiveData<PagingData<ItemData>>()
    val list: LiveData<PagingData<ItemData>> get() = _list

    val itemDeleteObserve: MutableLiveData<Unit> = MutableLiveData()

    init {
        setList()
        getContent()
    }

    private fun getContent() {

        viewModelScope.launch {
            ItemRepository().getSamplePagingSource()
//                .cachedIn(viewModelScope)
                .collectLatest { pagingData ->
                    _list.value = pagingData
                }
        }
    }

    fun setItemDelete(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            itemDeleteObserve.postValue(deleteItem(id))
        }
    }
}
package com.andcorns2.paging3

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class ItemRepository {

    fun getSamplePagingSource(): Flow<PagingData<ItemData>> {
        return Pager(
            config =  PagingConfig(
                pageSize = 25,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { ItemPagingSource() }
        ).flow
    }
}
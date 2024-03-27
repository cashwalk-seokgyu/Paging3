package com.andcorns2.paging3

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import java.io.IOException

class ItemPagingSource: PagingSource<Int, ItemData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemData> {

        Log.d("PAGING_LOG", "load:${params.key}")
        val page = params.key ?: STARTING_PAGE
        params.loadSize
        return try {
            val data = getList(page = page)
            LoadResult.Page(
                data = data,
                prevKey = if (page == STARTING_PAGE) null else page-1,
                nextKey = if(data.isEmpty()) null else page+1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ItemData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}

private const val STARTING_PAGE = 0 // 초기 페이지

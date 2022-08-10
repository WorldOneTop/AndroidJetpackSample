package com.example.jetpacksample

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState


class UserPagingSource(
    private val service: Request,
) : PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val page = params.key ?: 1
        return try {
            val response = service.getUser(page)
            val user = response.user
            LoadResult.Page(
                data = user,
                prevKey = page % 2 -1,
                nextKey = page % 2 +1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}

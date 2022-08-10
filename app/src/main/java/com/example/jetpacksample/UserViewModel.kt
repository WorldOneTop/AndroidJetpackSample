package com.example.jetpacksample

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UserViewModel(private val type: Int, private val dao: UserDao) : ViewModel() {
    var data: Flow<PagingData<User>>

    init {
        data = if (type == PagingActivity.TYPE_ROOM) {
            Pager(
                config = PagingConfig(
                    pageSize = 10,
                    enablePlaceholders = false,
                    maxSize = 50
                )
            ) {
                dao.allSelect()
            }.flow.cachedIn(viewModelScope)

        } else {
            Pager(
                config = PagingConfig(
                    pageSize = 6,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = { UserPagingSource(RetrofitAPI.request) }
            ).flow.cachedIn(viewModelScope)
        }
    }

}

class UserVMFactory(private val type: Int, private val dao: UserDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewModel(type, dao) as T
    }
}
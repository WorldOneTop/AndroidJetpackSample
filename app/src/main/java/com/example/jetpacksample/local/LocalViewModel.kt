package com.example.jetpacksample

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.jetpacksample.local.RoomDB
import com.example.jetpacksample.local.Words
import com.example.jetpacksample.local.WordsDao
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MyViewModel(private val dao: WordsDao) : ViewModel() {
    val data: Flow<PagingData<Words>> = Pager(
        config = PagingConfig(
            pageSize = 60,
            enablePlaceholders = true,
            maxSize = 200
        )
    ) {
        dao.allSelect()
    }.flow.cachedIn(viewModelScope)


    fun insert(text: CharSequence) = viewModelScope.launch(IO) {
        dao.insert(Words(id = 0, name = text.toString()))
    }

    fun remove(cheese: Words) = viewModelScope.launch(IO) {
        dao.delete(cheese)
    }
}

class MyVMFactory(private val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val wordsDao = RoomDB.getInstance(app).wordsDao()
        return MyViewModel(wordsDao) as T
    }
}
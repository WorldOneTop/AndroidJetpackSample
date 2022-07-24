package com.example.jetpacksample

import androidx.lifecycle.*
import com.example.jetpacksample.room.User
import com.example.jetpacksample.room.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(var userDao:UserDao):ViewModel() {
    val currentID: MutableLiveData<Int> = MutableLiveData<Int>(0)
    val user: LiveData<User> = currentID.switchMap {
        userDao.getUserById(it)
    }

    fun addNewUser(){
        viewModelScope.launch(Dispatchers.IO) {
            userDao.insertAll(User(currentID.value ?: 0, "email","local","db",""))
        }
    }
    fun moveToIndex(index:Int){
        currentID.value = currentID.value?.plus(index)
    }


}
class UserVMFactory(private val param: UserDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            UserViewModel(param) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}
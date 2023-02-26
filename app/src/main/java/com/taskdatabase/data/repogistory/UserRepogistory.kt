package com.taskdatabase.data.repogistory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.taskdatabase.data.UserDatabase
import com.taskdatabase.data.db.UserData

class UserRepogistory (private val userDatabase: UserDatabase){
    fun saveUserData(userData: UserData){
        userDatabase.appDao().insertUser(userData)
    }
    val userLivedata: LiveData<UserData> get() = userDatabase.appDao().getUser()

    fun getUser(): UserData{
        return userDatabase.appDao().user()
    }
}
package com.taskdatabase.ui.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.taskdatabase.data.db.UserData
import com.taskdatabase.data.repogistory.UserRepogistory
import kotlinx.coroutines.*

class HomeViewModel(private val userRepogistory: UserRepogistory) : ViewModel() {
    private var userData = UserData(1,"","","")
    private var coroutineScopeForFirstName: CoroutineScope? = null
    private var coroutineScopeForLastName: CoroutineScope? = null
    private var coroutineScopeForPhone: CoroutineScope? = null

    init {
    }

    fun updateUser(userData: UserData){
        this.userData = userData
    }

    fun getUser(): UserData{
        return userRepogistory.getUser()
    }

    val userLiveData: LiveData<UserData> get() = userRepogistory.userLivedata

    fun onfirstNameChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        Log.w("tag", "onTextChanged $s")
        coroutineScopeForFirstName?.cancel()
        coroutineScopeForFirstName = CoroutineScope(Dispatchers.IO)
        coroutineScopeForFirstName?.launch {
            delay(300)
            userData.firstName = "$s"
            userRepogistory.saveUserData(userData)
        }
    }
    fun onlastNameChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        Log.w("tag", "onTextChanged $s")
        coroutineScopeForLastName?.cancel()
        coroutineScopeForLastName = CoroutineScope(Dispatchers.IO)
        coroutineScopeForLastName?.launch {
            delay(300)
            userData.lastName = "$s"
            userRepogistory.saveUserData(userData)
        }
    }
    fun onPhoneChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        Log.w("tag", "onTextChanged $s")
        coroutineScopeForPhone?.cancel()
        coroutineScopeForPhone = CoroutineScope(Dispatchers.IO)
        coroutineScopeForPhone?.launch {
            delay(300)
            userData.phone = "$s"
            userRepogistory.saveUserData(userData)
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScopeForFirstName?.cancel()
        coroutineScopeForLastName?.cancel()
        coroutineScopeForPhone?.cancel()
    }
}
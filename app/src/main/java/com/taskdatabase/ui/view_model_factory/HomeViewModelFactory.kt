package com.taskdatabase.ui.view_model_factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.taskdatabase.data.repogistory.UserRepogistory
import com.taskdatabase.ui.view_model.HomeViewModel

class HomeViewModelFactory(private val userRepogistory: UserRepogistory) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            HomeViewModel(this.userRepogistory) as T
        }else{
            throw IllegalArgumentException("View model not found")
        }
    }
}
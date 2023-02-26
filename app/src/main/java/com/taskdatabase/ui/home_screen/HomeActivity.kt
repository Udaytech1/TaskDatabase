package com.taskdatabase.ui.home_screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.taskdatabase.R
import com.taskdatabase.data.UserDatabase
import com.taskdatabase.data.repogistory.UserRepogistory
import com.taskdatabase.databinding.ActivityHomeBinding
import com.taskdatabase.ui.view_model.HomeViewModel
import com.taskdatabase.ui.view_model_factory.HomeViewModelFactory

class HomeActivity : AppCompatActivity() {
    private var binding: ActivityHomeBinding? = null
    private var homeViewModel: HomeViewModel? = null
    private lateinit var userRepogistory: UserRepogistory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        userRepogistory = UserRepogistory(UserDatabase.getDBInstant(this))
        homeViewModel = ViewModelProvider(
            this,
            HomeViewModelFactory(userRepogistory)
        )[HomeViewModel::class.java]
        binding?.viewModel = homeViewModel
        binding?.lifecycleOwner = this
        initView()
    }

    private fun initView() {
        homeViewModel?.userLiveData?.observe(this, Observer {
            Log.d("TAG", "initView: ${it?.lastName}")
            binding?.apply {
                firstName = it?.firstName?:""
                lastName = it?.lastName?:""
                phone = it?.phone?:""
                executePendingBindings()
            }
            it?.let {user->
                homeViewModel?.updateUser(user)
            }
        })

        val userData = homeViewModel?.getUser()
        binding?.apply {
            editFirstName.setText("${userData?.firstName?:""}")
            editlastName.setText("${userData?.lastName?:""}")
            ediPhone.setText("${userData?.phone?:""}")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
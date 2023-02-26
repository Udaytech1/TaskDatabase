package com.taskdatabase.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserData(
    @PrimaryKey
    val id: Int,
    var firstName: String,
    var lastName: String,
    var phone: String
)
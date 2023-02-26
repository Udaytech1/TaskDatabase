package com.taskdatabase.data
import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.taskdatabase.data.db.UserData

@androidx.room.Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(todoData: UserData)

    @Query("Select * from UserData")
    fun getUser(): LiveData<UserData>

    @Query("Select * from UserData")
    fun user(): UserData
}
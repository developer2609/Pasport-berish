package com.example.passportnew.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao

interface MyUserDao {

  @Insert
   fun addUser(user: User)

  @Query("select* from user ")
  fun getAllContact():List<User>
}
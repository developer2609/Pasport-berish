package com.example.passportnew.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)

   abstract class AppDatabase: RoomDatabase() {

   abstract fun myuserDao():MyUserDao

   companion object{

      private  var  appDatabase: AppDatabase?=null
      @Synchronized
      fun getInstance(context: Context):AppDatabase{

         if (appDatabase==null){

            appDatabase= Room.databaseBuilder(context,AppDatabase::class.java,"pasport")
               .allowMainThreadQueries()
               .fallbackToDestructiveMigration()
               .build()
         }
         return appDatabase!!
      }

   }



}
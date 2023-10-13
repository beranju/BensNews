package com.beran.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.beran.core.data.local.entity.NewsEntity

@Database(
    entities = [NewsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class NewDatabase: RoomDatabase(){
    abstract fun newDao(): NewsDao
}
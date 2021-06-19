package com.mnhyim.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mnhyim.core.data.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class CatalogRoomDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}
package com.erfolgi.moviecataloguepro.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.erfolgi.moviecataloguepro.data.source.local.entity.MovieEntity
import com.erfolgi.moviecataloguepro.data.source.local.entity.TVEntity

@Database(entities = [MovieEntity::class, TVEntity::class],
    version = 1,
    exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun academyDao(): AppDao
    companion object {

        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(context.applicationContext,
                    AppDataBase::class.java,
                    "MovieCatalogue.db").build()
            }
    }
}
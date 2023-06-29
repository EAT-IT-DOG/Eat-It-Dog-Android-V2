package com.stac.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.stac.data.database.dao.AccountDao
import com.stac.data.database.dao.TokenDao
import com.stac.data.database.entity.AccountEntity
import com.stac.data.database.entity.TokenEntity
import java.util.concurrent.Executors

@Database(
    entities = [
        TokenEntity::class, AccountEntity::class,
    ],
    version = 9,
    exportSchema = false
)
abstract class RoomDatabase : androidx.room.RoomDatabase() {

    abstract fun tokenDao(): TokenDao
    abstract fun accountDao(): AccountDao

    companion object {
        private var instance: RoomDatabase? = null

        @Synchronized
        fun getInstance(context: Context): RoomDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDatabase::class.java, "eatitdog_database"
                )
                    .setQueryCallback({ sqlQuery, bindArgs ->
                        println("SQL Query: $sqlQuery SQL Args: $bindArgs")
                    }, Executors.newSingleThreadExecutor())
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
                return instance
            }
        }
    }
    
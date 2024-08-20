package com.mdubovikov.narutodb.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mdubovikov.narutodb.data.local.model.BookmarkModel

@Database(entities = [BookmarkModel::class], version = 1, exportSchema = false)
abstract class BookmarksDatabase : RoomDatabase() {

    abstract fun bookmarksDao(): BookmarksDao

    companion object {

        private const val DB_NAME = "BookmarksDatabase"
        private var INSTANCE: BookmarksDatabase? = null
        private val LOCK = Any()

        fun getInstance(context: Context): BookmarksDatabase {
            INSTANCE?.let { return it }

            synchronized(LOCK) {
                INSTANCE?.let { return it }

                val database = Room.databaseBuilder(
                    context = context,
                    klass = BookmarksDatabase::class.java,
                    name = DB_NAME
                ).build()

                INSTANCE = database
                return database
            }
        }
    }
}
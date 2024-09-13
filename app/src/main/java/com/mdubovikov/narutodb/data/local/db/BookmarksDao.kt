package com.mdubovikov.narutodb.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mdubovikov.narutodb.data.local.model.BookmarkModel
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarksDao {

    @Query("SELECT * FROM bookmarks")
    fun getBookmarks(): Flow<List<BookmarkModel>>

    @Query("SELECT EXISTS (SELECT * FROM bookmarks WHERE id=:itemId LIMIT 1)")
    fun observeIsBookmark(itemId: Int): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToBookmarks(itemDbModel: BookmarkModel)

    @Query("DELETE FROM bookmarks WHERE id=:itemId")
    suspend fun removeFromBookmarks(itemId: Int)
}
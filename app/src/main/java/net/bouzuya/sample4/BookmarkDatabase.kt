package net.bouzuya.sample4

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Bookmark::class], version = 1)
abstract class BookmarkDatabase : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao

    companion object {
        @Volatile
        private var _database: BookmarkDatabase? = null

        fun getDatabase(context: Context): BookmarkDatabase {
            return _database ?: synchronized(this) {
                _database ?: Room.databaseBuilder(
                    context.applicationContext,
                    BookmarkDatabase::class.java,
                    "bookmark_database"
                ).build().also { db ->
                    _database = db
                }
            }
        }
    }
}

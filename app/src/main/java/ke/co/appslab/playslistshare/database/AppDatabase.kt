package ke.co.appslab.playslistshare.database

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ke.co.appslab.playslistshare.database.dao.PlaylistDao
import ke.co.appslab.playslistshare.database.dao.SongDao
import ke.co.appslab.playslistshare.database.dao.UserDao
import ke.co.appslab.playslistshare.models.Playlist
import ke.co.appslab.playslistshare.models.Song
import ke.co.appslab.playslistshare.models.User

@Database(entities = [Playlist::class, User::class, Song::class], exportSchema = false, version = 1)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase? {
            when (INSTANCE) {
                null -> INSTANCE = Room.databaseBuilder(
                    context, AppDatabase::class.java,
                    "PlaylistShare"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    abstract fun userDao(): UserDao

    abstract fun playlistDao(): PlaylistDao

    abstract fun songDao(): SongDao
}
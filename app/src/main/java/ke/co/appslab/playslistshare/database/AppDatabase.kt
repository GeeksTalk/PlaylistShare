package ke.co.appslab.playslistshare.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import ke.co.appslab.playslistshare.database.dao.PlaylistDao
import ke.co.appslab.playslistshare.database.dao.UserDao

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
}
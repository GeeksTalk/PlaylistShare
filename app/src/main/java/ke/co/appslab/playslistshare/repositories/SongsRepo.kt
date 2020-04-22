package ke.co.appslab.playslistshare.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import ke.co.appslab.playslistshare.database.AppDatabase
import ke.co.appslab.playslistshare.database.dao.SongDao
import ke.co.appslab.playslistshare.models.Song

interface SongsRepo {
    suspend fun insertSong(song: Song)
    fun fetchAllSongs(): LiveData<List<Song>>
}

class SongsRepoImpl(application: Application) : SongsRepo {
    private val songDao: SongDao by lazy{
        AppDatabase.getDatabase(application)!!.songDao()
    }

    override suspend fun insertSong(song: Song) {
        songDao.insertSong(song)
    }

    override fun fetchAllSongs(): LiveData<List<Song>> {
        return songDao.fetchAllSongs()
    }

}
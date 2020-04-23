package ke.co.appslab.playslistshare.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import ke.co.appslab.playslistshare.database.AppDatabase
import ke.co.appslab.playslistshare.database.dao.SongDao
import ke.co.appslab.playslistshare.models.Song
import ke.co.appslab.playslistshare.models.SongsAndPlaylist

interface SongsRepo {
    suspend fun insertSong(song: Song)
    fun fetchAllSongs(): LiveData<List<Song>>
    fun fetchSongsAndPlaylist(songId: Long): LiveData<List<SongsAndPlaylist>>
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

    override fun fetchSongsAndPlaylist(songId: Long): LiveData<List<SongsAndPlaylist>> {
        return songDao.fetchSongsAndPlaylist(songId)
    }

}
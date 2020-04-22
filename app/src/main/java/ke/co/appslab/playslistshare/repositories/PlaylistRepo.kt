package ke.co.appslab.playslistshare.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import ke.co.appslab.playslistshare.database.AppDatabase
import ke.co.appslab.playslistshare.database.dao.PlaylistDao
import ke.co.appslab.playslistshare.models.Playlist
import ke.co.appslab.playslistshare.models.PlaylistAndUser

interface PlaylistRepo {
    suspend fun savePlaylist(playlist: Playlist)
    fun fetchPlaylists(): LiveData<List<Playlist>>
    fun getPlaylistUser(playlistId: Int): LiveData<List<PlaylistAndUser>>
}

class PlaylistRepoImpl(application: Application) : PlaylistRepo {
    private val playlistDao: PlaylistDao by lazy {
        AppDatabase.getDatabase(application)!!.playlistDao()
    }

    override suspend fun savePlaylist(playlist: Playlist) {
        playlistDao.savePlaylist(playlist)
    }

    override fun fetchPlaylists(): LiveData<List<Playlist>> {
        return playlistDao.fetchPlaylists()
    }

    override fun getPlaylistUser(playlistId: Int): LiveData<List<PlaylistAndUser>> {
        return playlistDao.getPlaylistUser(playlistId)
    }

}
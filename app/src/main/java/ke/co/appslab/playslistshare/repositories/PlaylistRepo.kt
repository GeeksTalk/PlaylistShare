package ke.co.appslab.playslistshare.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import ke.co.appslab.playslistshare.database.AppDatabase
import ke.co.appslab.playslistshare.database.dao.PlaylistDao
import ke.co.appslab.playslistshare.models.Playlist
import ke.co.appslab.playslistshare.models.PlaylistAndSongs
import ke.co.appslab.playslistshare.models.PlaylistAndUser
import ke.co.appslab.playslistshare.models.PlaylistSongsCrossRef

interface PlaylistRepo {
    suspend fun savePlaylist(playlist: Playlist): Long
    fun fetchPlaylists(): LiveData<List<Playlist>>
    fun getPlaylistUser(playlistId: Long): LiveData<List<PlaylistAndUser>>
    suspend fun savePlaylistSongs(playlistSongsCrossRef: PlaylistSongsCrossRef)
    fun fetchPlaylistAndSongs(playlistId: Long): LiveData<List<PlaylistAndSongs>>
}

class PlaylistRepoImpl(application: Application) : PlaylistRepo {
    private val playlistDao: PlaylistDao by lazy {
        AppDatabase.getDatabase(application)!!.playlistDao()
    }

    override suspend fun savePlaylist(playlist: Playlist): Long {
        return playlistDao.savePlaylist(playlist)
    }

    override fun fetchPlaylists(): LiveData<List<Playlist>> {
        return playlistDao.fetchPlaylists()
    }

    override fun getPlaylistUser(playlistId: Long): LiveData<List<PlaylistAndUser>> {
        return playlistDao.getPlaylistUser(playlistId)
    }

    override suspend fun savePlaylistSongs(playlistSongsCrossRef: PlaylistSongsCrossRef) {
        playlistDao.savePlaylistSongs(playlistSongsCrossRef)
    }

    override fun fetchPlaylistAndSongs(playlistId: Long): LiveData<List<PlaylistAndSongs>> {
        return playlistDao.fetchPlaylistAndSongs(playlistId)
    }

}
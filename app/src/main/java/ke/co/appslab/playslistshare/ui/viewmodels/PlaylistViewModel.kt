package ke.co.appslab.playslistshare.ui.viewmodels

import android.app.Application
import androidx.lifecycle.*
import ke.co.appslab.playslistshare.models.Playlist
import ke.co.appslab.playslistshare.models.PlaylistAndSongs
import ke.co.appslab.playslistshare.models.PlaylistAndUser
import ke.co.appslab.playslistshare.models.PlaylistSongsCrossRef
import ke.co.appslab.playslistshare.repositories.PlaylistRepoImpl
import ke.co.appslab.playslistshare.repositories.SongsRepoImpl
import ke.co.appslab.playslistshare.repositories.UserRepoImpl
import kotlinx.coroutines.launch

class PlaylistViewModel(application: Application) : AndroidViewModel(application) {
    private val playlistRepo = PlaylistRepoImpl(application)
    private val userRepo = UserRepoImpl(application)
    private val songsRepo = SongsRepoImpl(application)
    val playlists = playlistRepo.fetchPlaylists()
    val users = userRepo.getAllUsers()
    val songs = songsRepo.fetchAllSongs()
    private val playlistId = MutableLiveData<Long>()

    fun savePlaylist(playlist: Playlist): MutableLiveData<Long> {
        viewModelScope.launch {
            playlistId.value = playlistRepo.savePlaylist(playlist)
        }
        return playlistId
    }

    fun getPlaylistUsers(playlistId: Long): LiveData<List<PlaylistAndUser>> {
        return playlistRepo.getPlaylistUser(playlistId)
    }

    fun savePlaylistSongs(playlistSongsCrossRef: PlaylistSongsCrossRef) {
        viewModelScope.launch {
            playlistRepo.savePlaylistSongs(playlistSongsCrossRef)
        }
    }

    fun getSongsInPlaylists(playlistId: Long): LiveData<List<PlaylistAndSongs>> {
        return playlistRepo.fetchPlaylistAndSongs(playlistId)
    }
}
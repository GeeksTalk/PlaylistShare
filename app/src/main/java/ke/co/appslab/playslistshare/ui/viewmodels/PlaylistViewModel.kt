package ke.co.appslab.playslistshare.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import ke.co.appslab.playslistshare.models.Playlist
import ke.co.appslab.playslistshare.models.PlaylistAndUser
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

    fun savePlaylist(playlist: Playlist) {
        viewModelScope.launch {
            playlistRepo.savePlaylist(playlist)
        }
    }

    fun getPlaylistUsers(playlistId: Int): LiveData<List<PlaylistAndUser>> {
        return playlistRepo.getPlaylistUser(playlistId)
    }
}
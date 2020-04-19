package ke.co.appslab.playslistshare.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import ke.co.appslab.playslistshare.models.Playlist
import ke.co.appslab.playslistshare.repositories.PlaylistRepoImpl
import kotlinx.coroutines.launch

class PlaylistViewModel(application: Application) : AndroidViewModel(application) {
    private val playlistRepo = PlaylistRepoImpl(application)
    val playlists = playlistRepo.fetchPlaylists()

    fun savePlaylist(playlist: Playlist) {
        viewModelScope.launch {
            playlistRepo.savePlaylist(playlist)
        }
    }
}
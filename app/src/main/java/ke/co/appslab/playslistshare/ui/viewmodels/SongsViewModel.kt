package ke.co.appslab.playslistshare.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import ke.co.appslab.playslistshare.models.Song
import ke.co.appslab.playslistshare.repositories.SongsRepoImpl
import kotlinx.coroutines.launch

class SongsViewModel(application: Application) : AndroidViewModel(application) {
    private val songsRepo = SongsRepoImpl(application)
    val songs = songsRepo.fetchAllSongs()


    fun insertSong(song: Song) {
        viewModelScope.launch {
            songsRepo.insertSong(song)
        }
    }
}
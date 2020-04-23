package ke.co.appslab.playslistshare.ui.views.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import ke.co.appslab.playslistshare.R
import ke.co.appslab.playslistshare.models.Playlist
import ke.co.appslab.playslistshare.models.PlaylistAndSongs
import ke.co.appslab.playslistshare.models.PlaylistAndUser
import ke.co.appslab.playslistshare.models.User
import ke.co.appslab.playslistshare.ui.adapters.SongsAdapter
import ke.co.appslab.playslistshare.ui.adapters.UserAdapter
import ke.co.appslab.playslistshare.ui.viewmodels.PlaylistViewModel
import kotlinx.android.synthetic.main.fragment_playlist_details.*

class PlaylistDetailsFragment : Fragment(R.layout.fragment_playlist_details) {
    private val playlistViewModel: PlaylistViewModel by viewModels()
    private val playlistDetailsFragmentArgs: PlaylistDetailsFragmentArgs by navArgs()
    private val playlist: Playlist by lazy {
        playlistDetailsFragmentArgs.playlist
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getPlaylistUser()
        getSongsInPlayList()
    }

    private fun getSongsInPlayList() {
        playlistViewModel.getSongsInPlaylists(playlist.playlistId).observe(
            viewLifecycleOwner,
            Observer { playlistAndSongs ->
                if (playlistAndSongs.isNotEmpty()) {
                    showSongsInPlaylist(playlistAndSongs)
                }
            })
    }

    private fun showSongsInPlaylist(playlistAndSongs: List<PlaylistAndSongs>) {
        val adapter = SongsAdapter(playlistAndSongs[0].songs){

        }
        rvSongPlaylist.adapter = adapter
    }

    private fun getPlaylistUser() {
        playlistViewModel.getPlaylistUsers(playlist.playlistId)
            .observe(viewLifecycleOwner, Observer { playlistUsers ->
                if (playlistUsers.isNotEmpty()) {
                    initView(playlistUsers)
                }
            })
    }

    private fun initView(playlistUsers: List<PlaylistAndUser>) {
        val adapter = UserAdapter(playlistUsers[0].users) {

        }
        rvPlaylistUser.adapter = adapter

    }
}
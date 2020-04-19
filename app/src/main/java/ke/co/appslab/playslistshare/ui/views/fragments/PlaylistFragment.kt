package ke.co.appslab.playslistshare.ui.views.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import ke.co.appslab.playslistshare.R
import ke.co.appslab.playslistshare.models.Playlist
import ke.co.appslab.playslistshare.ui.adapters.PlaylistAdapter
import ke.co.appslab.playslistshare.ui.viewmodels.PlaylistViewModel
import ke.co.appslab.playslistshare.utils.hide
import ke.co.appslab.playslistshare.utils.show
import kotlinx.android.synthetic.main.fragment_playlist.*

class PlaylistFragment : Fragment(R.layout.fragment_playlist) {
    private val playlistViewModel: PlaylistViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabNewPlaylist.setOnClickListener {
            findNavController().navigate(R.id.action_playlistFragment_to_newPlaylistFragment)
        }

        fetchPlaylists()
    }

    private fun fetchPlaylists() {
        playlistViewModel.playlists.observe(viewLifecycleOwner, Observer { playlists ->
            if (playlists.isEmpty()) {
                tvEmptyText.show()
                rvPlaylist.hide()
            } else {
                tvEmptyText.hide()
                rvPlaylist.show()
                initView(playlists)
            }
        })
    }

    private fun initView(playlists: List<Playlist>) {
        val playlistAdapter = PlaylistAdapter(playlists){

        }
        rvPlaylist.adapter = playlistAdapter
    }
}
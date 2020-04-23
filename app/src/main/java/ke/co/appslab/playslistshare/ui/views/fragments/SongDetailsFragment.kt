package ke.co.appslab.playslistshare.ui.views.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import ke.co.appslab.playslistshare.R
import ke.co.appslab.playslistshare.models.Song
import ke.co.appslab.playslistshare.models.SongsAndPlaylist
import ke.co.appslab.playslistshare.ui.adapters.PlaylistAdapter
import ke.co.appslab.playslistshare.ui.viewmodels.SongsViewModel
import kotlinx.android.synthetic.main.fragment_song_details.*

class SongDetailsFragment : Fragment(R.layout.fragment_song_details) {
    private val songsViewModel: SongsViewModel by viewModels()
    private val songDetailsFragmentArgs: SongDetailsFragmentArgs by navArgs()
    private val song: Song by lazy {
        songDetailsFragmentArgs.song
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchPlaylists()
    }

    private fun fetchPlaylists() {
        songsViewModel.fetchSongAndPlaylist(song.songId)
            .observe(viewLifecycleOwner, Observer { songAndPlaylist ->
                if (songAndPlaylist.isNotEmpty()) {
                    initView(songAndPlaylist)
                }
            })
    }

    private fun initView(songAndPlaylist: List<SongsAndPlaylist>) {
        val adapter = PlaylistAdapter(songAndPlaylist[0].playlists){}
        rvSongPlaylist.adapter = adapter

    }
}
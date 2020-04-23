package ke.co.appslab.playslistshare.ui.views.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import ke.co.appslab.playslistshare.R
import ke.co.appslab.playslistshare.models.Song
import ke.co.appslab.playslistshare.ui.adapters.SongsAdapter
import ke.co.appslab.playslistshare.ui.viewmodels.SongsViewModel
import ke.co.appslab.playslistshare.utils.hide
import ke.co.appslab.playslistshare.utils.show
import kotlinx.android.synthetic.main.fragment_songs.*


class SongsFragment : Fragment(R.layout.fragment_songs) {
    private val songsViewModel: SongsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeSongs()
        fabNewSong.setOnClickListener {
            findNavController().navigate(R.id.action_songsFragment_to_newSongFragment)
        }
    }

    private fun observeSongs() {
        songsViewModel.songs.observe(viewLifecycleOwner, Observer { songs ->
            if (songs.isEmpty()) {
                tvEmptyText.show()
                rvSongs.hide()
            } else {
                tvEmptyText.hide()
                rvSongs.show()
                initView(songs)
            }
        })
    }

    private fun initView(songs: List<Song>) {
        val songsAdapter = SongsAdapter(songs) { song ->
            val actionSong = SongsFragmentDirections.actionSongsFragmentToSongDetailsFragment(song)
            findNavController().navigate(actionSong)

        }
        rvSongs.adapter = songsAdapter

    }
}
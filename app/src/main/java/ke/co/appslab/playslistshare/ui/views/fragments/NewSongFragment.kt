package ke.co.appslab.playslistshare.ui.views.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ke.co.appslab.playslistshare.R
import ke.co.appslab.playslistshare.models.Song
import ke.co.appslab.playslistshare.ui.viewmodels.SongsViewModel
import ke.co.appslab.playslistshare.utils.toast
import kotlinx.android.synthetic.main.fragment_new_song.*

class NewSongFragment : Fragment(R.layout.fragment_new_song) {
    private val songsViewModel: SongsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSaveSong.setOnClickListener {
            val songName = editSongName.text.toString()
            val songArtistName = editArtistName.text.toString()
            saveSong(songName, songArtistName)
        }
    }

    private fun saveSong(songName: String, songArtistName: String) {
        if (validateInputs(songName, songArtistName)) {
            val song = Song(0, songName, songArtistName)
            songsViewModel.insertSong(song)
            requireContext().toast("Song added!")
            findNavController().navigate(R.id.action_newSongFragment_to_songsFragment)

        }
    }

    private fun validateInputs(songName: String, songArtistName: String): Boolean {
        var valid = false
        when {
            TextUtils.isEmpty(songName) -> {
                layoutSongName.error = getString(R.string.error_song_name_required)
                editSongName.requestFocus()
                valid = false
            }
            else -> {
                layoutSongName.error = null
                valid = true
            }
        }
        when {
            TextUtils.isEmpty(songArtistName) -> {
                layoutArtistName.error = getString(R.string.error_artist_name)
                editArtistName.requestFocus()
                valid = false
            }
            else -> {
                layoutArtistName.error = null
                valid = true
            }
        }
        return valid
    }
}
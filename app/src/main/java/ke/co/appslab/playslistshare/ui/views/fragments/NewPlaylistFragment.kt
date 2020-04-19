package ke.co.appslab.playslistshare.ui.views.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ke.co.appslab.playslistshare.R
import ke.co.appslab.playslistshare.models.Playlist
import ke.co.appslab.playslistshare.ui.viewmodels.PlaylistViewModel
import ke.co.appslab.playslistshare.utils.toast
import kotlinx.android.synthetic.main.fragment_new_playslist.*

class NewPlaylistFragment : Fragment(R.layout.fragment_new_playslist) {
    lateinit var playlistType: String
    private val playlistViewModel: PlaylistViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toggleButtonListen()
        btnSharePlaylist.setOnClickListener {
            val playlistName = editPlaylistName.text.toString()
            val playlistLink = editPlaylistLink.text.toString()
            sharePlaylist(playlistName, playlistLink)
        }
    }

    private fun sharePlaylist(playlistName: String, playlistLink: String) {
        if (validateInputs(playlistName, playlistLink)) {
            val playlist = Playlist(0, playlistName, playlistLink, playlistType)
            playlistViewModel.savePlaylist(playlist)
            requireContext().toast("Playlist Shared")
            findNavController().navigate(R.id.action_newPlaylistFragment_to_playlistFragment)
        }
    }

    private fun validateInputs(playlistName: String, playlistLink: String): Boolean {
        var valid: Boolean = false

        when {
            TextUtils.isEmpty(playlistName) -> {
                layoutName.error = getString(R.string.error_playlist_name_required)
                editPlaylistName.requestFocus()
                valid = false
            }
            else -> {
                layoutName.error = null
                valid = true
            }
        }
        when {
            TextUtils.isEmpty(playlistLink) -> {
                layoutLink.error = getString(R.string.error_playlist_link_required)
                editPlaylistLink.requestFocus()
                valid = false
            }
            else -> {
                layoutLink.error = null
                valid = true
            }
        }
        return valid
    }

    private fun toggleButtonListen() {
        btnGroupType.addOnButtonCheckedListener { group, checkedId, isChecked ->
            playlistType = when (checkedId) {
                R.id.btnSingle -> "Single"
                R.id.btnAlbum -> "Album"
                R.id.btnMix -> "Mix"
                else -> "None Selected"
            }

        }
    }
}
package ke.co.appslab.playslistshare.ui.views.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import ke.co.appslab.playslistshare.R
import ke.co.appslab.playslistshare.models.Playlist
import ke.co.appslab.playslistshare.models.PlaylistSongsCrossRef
import ke.co.appslab.playslistshare.models.Song
import ke.co.appslab.playslistshare.models.User
import ke.co.appslab.playslistshare.ui.viewmodels.PlaylistViewModel
import ke.co.appslab.playslistshare.utils.hide
import ke.co.appslab.playslistshare.utils.show
import ke.co.appslab.playslistshare.utils.toast
import kotlinx.android.synthetic.main.fragment_new_playslist.*

class NewPlaylistFragment : Fragment(R.layout.fragment_new_playslist) {
    lateinit var playlistType: String
    private val playlistViewModel: PlaylistViewModel by viewModels()
    private var songs = emptyList<Song>()
    private var userId: Long = 0L


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toggleButtonListen()
        btnSharePlaylist.setOnClickListener {
            val playlistName = editPlaylistName.text.toString()
            val playlistLink = editPlaylistLink.text.toString()
            sharePlaylist(playlistName, playlistLink)
        }
        observeLivedata()
    }

    private fun observeLivedata() {
        playlistViewModel.users.observe(viewLifecycleOwner, Observer { users ->
            setupUsersSpinner(users)
        })
        playlistViewModel.songs.observe(viewLifecycleOwner, Observer { songsList ->
            songs = songsList
        })
    }

    private fun setupUsersSpinner(users: List<User>) {
        val usersSpinnerAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, users)
        usersSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        userSpinner.adapter = usersSpinnerAdapter
        userSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val user = userSpinner.selectedItem as User
                userId = user.id
            }


        }

    }

    private fun sharePlaylist(playlistName: String, playlistLink: String) {
        if (validateInputs(playlistName, playlistLink)) {
            val playlist = Playlist(0, playlistName, playlistLink, playlistType, userId)
            playlistViewModel.savePlaylist(playlist)
                .observe(viewLifecycleOwner, Observer { playlistId ->
                    chipGroupSongs.checkedChipIds.forEach { songId ->
                        val playlistCrossRef = PlaylistSongsCrossRef(playlistId, songId.toLong())
                        playlistViewModel.savePlaylistSongs(playlistCrossRef)

                    }
                    requireContext().toast("Playlist Shared")
                    findNavController().navigate(R.id.action_newPlaylistFragment_to_playlistFragment)
                })
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
            when (checkedId) {
                R.id.btnSingle -> {
                    playlistType = "Single"
                    layoutSongs.hide()
                }
                R.id.btnAlbum -> {
                    playlistType = "Album"
                    layoutSongs.show()
                    showSongsChip()
                }
                R.id.btnMix -> {
                    playlistType = "Mix"
                    layoutSongs.hide()
                }
                else -> {
                    playlistType = "None Selected"
                    layoutSongs.hide()
                }
            }

        }
    }

    private fun showSongsChip() {
        songs.forEach {
            val chip =
                LayoutInflater.from(requireContext()).inflate(R.layout.song_chip, null) as Chip
            chip.text = it.songName
            chipGroupSongs.addView(chip)
        }


    }

    private fun getSelectedSongs() {
        chipGroupSongs.checkedChipIds.forEach {
            requireActivity().toast(it.toString())
        }

    }
}
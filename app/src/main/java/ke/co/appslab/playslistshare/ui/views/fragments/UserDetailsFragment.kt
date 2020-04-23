package ke.co.appslab.playslistshare.ui.views.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import ke.co.appslab.playslistshare.R
import ke.co.appslab.playslistshare.models.User
import ke.co.appslab.playslistshare.models.UserAndPlaylists
import ke.co.appslab.playslistshare.ui.adapters.PlaylistAdapter
import ke.co.appslab.playslistshare.ui.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.fragment_user_details.*

class UserDetailsFragment : Fragment(R.layout.fragment_user_details) {
    private val userViewModel: UserViewModel by viewModels()
    private val userDetailsFragmentArgs: UserDetailsFragmentArgs by navArgs()
    private val user: User by lazy {
        userDetailsFragmentArgs.user
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getUserPlaylists()
    }

    private fun getUserPlaylists() {
        userViewModel.getUserPlaylists(user.id)
            .observe(viewLifecycleOwner, Observer { userPlaylists ->
                if (userPlaylists.isNotEmpty()) {
                    initView(userPlaylists)
                }
            })
    }

    private fun initView(userPlaylists: List<UserAndPlaylists>) {
        val adapter = PlaylistAdapter(userPlaylists[0].playlists) {

        }
        rvUserPlaylist.adapter = adapter
    }
}
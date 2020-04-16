package ke.co.appslab.playslistshare.ui.views.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import ke.co.appslab.playslistshare.R
import ke.co.appslab.playslistshare.models.User
import ke.co.appslab.playslistshare.ui.adapters.UserAdapter
import ke.co.appslab.playslistshare.ui.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.fragment_users.*

class UsersFragment : Fragment(R.layout.fragment_users) {
    private val userViewModel: UserViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchUsers()
    }

    private fun fetchUsers() {
        userViewModel.users.observe(viewLifecycleOwner, Observer { users ->
            initView(users)
        })
    }

    private fun initView(users: List<User>) {
        val userAdapter = UserAdapter(users) {

        }
        rvUsers.adapter = userAdapter

    }
}
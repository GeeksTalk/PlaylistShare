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
import ke.co.appslab.playslistshare.utils.hide
import ke.co.appslab.playslistshare.utils.show
import kotlinx.android.synthetic.main.fragment_users.*

class UsersFragment : Fragment(R.layout.fragment_users) {
    private val userViewModel: UserViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabRegisterUser.setOnClickListener {
            findNavController().navigate(R.id.action_usersFragment_to_registerFragment)
        }

        fetchUsers()
    }

    private fun fetchUsers() {
        userViewModel.users.observe(viewLifecycleOwner, Observer { users ->
            if (users.isEmpty()) {
                tvEmptyText.show()
                rvUsers.hide()
            } else {
                tvEmptyText.hide()
                rvUsers.show()
                initView(users)
            }

        })
    }

    private fun initView(users: List<User>) {
        val userAdapter = UserAdapter(users) { user ->
            val actionUser = UsersFragmentDirections.actionUsersFragmentToUserDetailsFragment(user)
            findNavController().navigate(actionUser)
        }
        rvUsers.adapter = userAdapter

    }
}
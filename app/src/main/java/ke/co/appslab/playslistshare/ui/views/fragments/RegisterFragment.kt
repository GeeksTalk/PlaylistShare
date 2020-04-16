package ke.co.appslab.playslistshare.ui.views.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ke.co.appslab.playslistshare.R
import ke.co.appslab.playslistshare.models.User
import ke.co.appslab.playslistshare.ui.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment(R.layout.fragment_register) {
    private val userViewModel: UserViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnRegister.setOnClickListener {
            val name = editFullName.text.toString()
            val email = editEmail.text.toString()
            registerUser(name, email)
        }
    }

    private fun registerUser(name: String, email: String) {
        val user = User(
            0,
            name,
            email
        )
        userViewModel.registerUser(user)
        Toast.makeText(requireContext(), "Successfully Registered", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_registerFragment_to_usersFragment2)
    }
}
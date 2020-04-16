package ke.co.appslab.playslistshare.ui.viewmodels

import android.app.Application
import androidx.lifecycle.*
import ke.co.appslab.playslistshare.models.User
import ke.co.appslab.playslistshare.repositories.UserRepo
import ke.co.appslab.playslistshare.repositories.UserRepoImpl
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    val userRepo: UserRepo = UserRepoImpl(application)
    val users = userRepo.getAllUsers()


    fun registerUser(user: User) {
        viewModelScope.launch {
            userRepo.registerUser(user)
        }
    }
}
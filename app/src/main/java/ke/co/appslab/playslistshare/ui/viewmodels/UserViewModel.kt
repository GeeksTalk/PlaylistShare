package ke.co.appslab.playslistshare.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ke.co.appslab.playslistshare.models.User
import ke.co.appslab.playslistshare.repositories.UserRepo
import ke.co.appslab.playslistshare.repositories.UserRepoImpl
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    val userRepo: UserRepo = UserRepoImpl(application)
    val users = MediatorLiveData<List<User>>()


    fun registerUser(user: User) {
        viewModelScope.launch {
            userRepo.registerUser(user)
        }
    }
}
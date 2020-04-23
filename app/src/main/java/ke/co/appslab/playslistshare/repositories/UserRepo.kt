package ke.co.appslab.playslistshare.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import ke.co.appslab.playslistshare.database.AppDatabase
import ke.co.appslab.playslistshare.database.dao.UserDao
import ke.co.appslab.playslistshare.models.User
import ke.co.appslab.playslistshare.models.UserAndPlaylists

interface UserRepo {
    suspend fun registerUser(user: User)
    fun getAllUsers(): LiveData<List<User>>
    fun getUserPlaylists(userId: Long): LiveData<List<UserAndPlaylists>>
}

class UserRepoImpl(application: Application) : UserRepo {
    private val userDao: UserDao by lazy {
        AppDatabase.getDatabase(application)!!.userDao()
    }

    override suspend fun registerUser(user: User) {
        userDao.registerUser(user)
    }

    override fun getAllUsers(): LiveData<List<User>> {
        return userDao.getAllUsers()
    }

    override fun getUserPlaylists(userId: Long): LiveData<List<UserAndPlaylists>> {
        return userDao.getUserPlaylists(userId)
    }

}
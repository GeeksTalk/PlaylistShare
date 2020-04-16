package ke.co.appslab.playslistshare.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import ke.co.appslab.playslistshare.models.User

@Dao
interface UserDao {

    @Insert(onConflict = REPLACE)
    suspend fun registerUser(user: User)

    @Query("SELECT * FROM User")
    fun getAllUsers(): LiveData<List<User>>

}
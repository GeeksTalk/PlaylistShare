package ke.co.appslab.playslistshare.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import ke.co.appslab.playslistshare.models.User

@Dao
interface UserDao {

    @Insert(onConflict = REPLACE)
    fun registerUser(user: User)

    @Query("SELECT * FROM User")
    fun getAllUsers(): LiveData<List<User>>

}
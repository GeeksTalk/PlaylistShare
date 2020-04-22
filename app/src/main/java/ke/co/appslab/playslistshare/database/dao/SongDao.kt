package ke.co.appslab.playslistshare.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import ke.co.appslab.playslistshare.models.Song

@Dao
interface SongDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertSong(song: Song)

    @Query("SELECT * FROM Songs")
    fun fetchAllSongs(): LiveData<List<Song>>
}
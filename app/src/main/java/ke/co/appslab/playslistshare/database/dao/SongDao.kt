package ke.co.appslab.playslistshare.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import ke.co.appslab.playslistshare.models.Song
import ke.co.appslab.playslistshare.models.SongsAndPlaylist

@Dao
interface SongDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertSong(song: Song)

    @Query("SELECT * FROM Songs")
    fun fetchAllSongs(): LiveData<List<Song>>

    @Transaction
    @Query("SELECT * FROM Songs WHERE songId = :songId")
    fun fetchSongsAndPlaylist(songId: Long): LiveData<List<SongsAndPlaylist>>
}
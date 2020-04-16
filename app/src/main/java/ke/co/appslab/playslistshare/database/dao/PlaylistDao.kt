package ke.co.appslab.playslistshare.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import ke.co.appslab.playslistshare.models.Playlist

@Dao
interface PlaylistDao {

    @Insert(onConflict = REPLACE)
    suspend fun savePlaylist(playlist: Playlist)

    @Query("SELECT * FROM Playlist")
    fun fetchPlaylists(): LiveData<List<Playlist>>
}
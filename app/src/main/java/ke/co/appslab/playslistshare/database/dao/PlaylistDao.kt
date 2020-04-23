package ke.co.appslab.playslistshare.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import ke.co.appslab.playslistshare.models.*

@Dao
interface PlaylistDao {

    @Insert(onConflict = REPLACE)
    suspend fun savePlaylist(playlist: Playlist): Long

    @Query("SELECT * FROM Playlist")
    fun fetchPlaylists(): LiveData<List<Playlist>>

    @Transaction
    @Query("SELECT * FROM Playlist WHERE playlistId = :playlistId ")
    fun getPlaylistUser(playlistId: Long): LiveData<List<PlaylistAndUser>>

    @Insert(onConflict = IGNORE)
    suspend fun savePlaylistSongs(playlistSongsCrossRef: PlaylistSongsCrossRef)

    @Transaction
    @Query("SELECT * FROM Playlist WHERE playlistId = :playlistId")
    fun fetchPlaylistAndSongs(playlistId: Long): LiveData<List<PlaylistAndSongs>>
}
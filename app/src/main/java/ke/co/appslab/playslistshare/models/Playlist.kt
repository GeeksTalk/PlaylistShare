package ke.co.appslab.playslistshare.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Playlist")
data class Playlist(
    @PrimaryKey(autoGenerate = true)
    val playlistId: Int,
    val playlistName: String,
    val playlistLink: String,
    val playlistType: String,
    val userId: String

)
package ke.co.appslab.playslistshare.models

import androidx.room.Entity
import java.util.*

@Entity(tableName = "Playlist")
data class Playlist(
    val playlistId: Int,
    val playlistName: String,
    val playlistLink: String,
    val playlistType: String

)
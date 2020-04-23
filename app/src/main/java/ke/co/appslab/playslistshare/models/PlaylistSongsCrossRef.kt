package ke.co.appslab.playslistshare.models

import androidx.room.Entity

@Entity(primaryKeys = ["playlistId", "songId"])
data class PlaylistSongsCrossRef(
    val playlistId: Long,
    val songId: Long
)
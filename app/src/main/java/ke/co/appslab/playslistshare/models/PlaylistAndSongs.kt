package ke.co.appslab.playslistshare.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class PlaylistAndSongs(
    @Embedded val playlist: Playlist,
    @Relation(
        parentColumn = "playlistId",
        entityColumn = "songId",
        associateBy = Junction(PlaylistSongsCrossRef::class)
    )
    val songs: List<Song>

)
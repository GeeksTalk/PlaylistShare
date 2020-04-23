package ke.co.appslab.playslistshare.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class SongsAndPlaylist(
    @Embedded val song: Song,
    @Relation(
        parentColumn = "songId",
        entityColumn = "playlistId",
        associateBy = Junction(PlaylistSongsCrossRef::class)
    )
    val playlists: List<Playlist>
)
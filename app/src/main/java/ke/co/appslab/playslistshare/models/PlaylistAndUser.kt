package ke.co.appslab.playslistshare.models

import androidx.room.Embedded
import androidx.room.Relation

data class PlaylistAndUser(
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val playlist: Playlist
)
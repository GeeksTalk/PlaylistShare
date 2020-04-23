package ke.co.appslab.playslistshare.models

import androidx.room.Embedded
import androidx.room.Relation

data class PlaylistAndUser(
    @Embedded val playlist: Playlist,
    @Relation(
        parentColumn = "userId",
        entityColumn = "id"
    )
    val users: List<User>
)
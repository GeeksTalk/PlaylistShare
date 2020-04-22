package ke.co.appslab.playslistshare.models

import androidx.room.Embedded
import androidx.room.Relation

data class UserAndPlaylists(
    @Embedded val user: User,
    @Relation(
        parentColumn = "id" ,
        entityColumn = "userId"
    )
    val playlists: List<Playlist>
)


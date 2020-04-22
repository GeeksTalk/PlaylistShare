package ke.co.appslab.playslistshare.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Songs")
data class Song(
    @PrimaryKey(autoGenerate = true)
    val songId: Int,
    val songName: String,
    val artist: String
)
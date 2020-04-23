package ke.co.appslab.playslistshare.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity(tableName = "Playlist")
@Parcelize
data class Playlist(
    @PrimaryKey(autoGenerate = true)
    val playlistId: Long,
    val playlistName: String,
    val playlistLink: String,
    val playlistType: String,
    val userId: Long

) : Parcelable
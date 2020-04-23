package ke.co.appslab.playslistshare.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Songs")
@Parcelize
data class Song(
    @PrimaryKey(autoGenerate = true)
    val songId: Long,
    val songName: String,
    val artist: String
) : Parcelable
package ke.co.appslab.playslistshare.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "User")
@Parcelize
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val email: String
) : Parcelable {
    override fun toString(): String {
        return name
    }
}
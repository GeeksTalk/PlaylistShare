package ke.co.appslab.playslistshare.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: String,
    val name: String,
    val email: String
)
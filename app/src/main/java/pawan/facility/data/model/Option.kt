package pawan.facility.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "options",
    foreignKeys = [ForeignKey(
        entity = Facility::class,
        parentColumns = arrayOf("facilityId"),
        childColumns = arrayOf("fId")
    )]
)
data class Option(
    @PrimaryKey var icon: String,
    var fId: String,
    var id: String,
    var name: String
)
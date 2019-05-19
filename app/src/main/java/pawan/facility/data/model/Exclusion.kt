package pawan.facility.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exclusions")
data class Exclusion(
    @PrimaryKey var facility_id: String,
    var options_id: String
)
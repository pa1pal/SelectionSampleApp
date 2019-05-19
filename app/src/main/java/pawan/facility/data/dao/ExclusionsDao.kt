package pawan.facility.data.dao

import androidx.room.*
import pawan.facility.data.model.Exclusion

/**
 * Created by Pawan Pal on 19/5/19.
 */
@Dao
interface ExclusionsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(exclusions: Exclusion)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(vararg exclusions: Exclusion)

    @Delete
    fun delete(vararg exclusions: Exclusion)

    @Query("SELECT * from exclusions where facility_id=:facilityId")
    fun getExclusionsByFacilityId(facilityId: Int): Exclusion
}
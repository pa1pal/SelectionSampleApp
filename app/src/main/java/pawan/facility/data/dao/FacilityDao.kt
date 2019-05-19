package pawan.facility.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import pawan.facility.data.model.Facility

/**
 * Created by Pawan Pal on 19/5/19.
 */
@Dao
interface FacilityDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(facility: Facility)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(vararg facility: Facility)

    @Delete
    fun delete(vararg facility: Facility)

    @Query("SELECT * from facilities")
    fun getAllFacility(): LiveData<List<Facility>>

    @Query("SELECT * from facilities where facilityId=:facilityId")
    fun getFacilityByFacilityId(facilityId: Int): Facility
}
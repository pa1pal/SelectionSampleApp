package pawan.facility.data.dao

import androidx.room.*
import pawan.facility.data.model.Option

/**
 * Created by Pawan Pal on 19/5/19.
 */
@Dao
interface OptionDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(option: Option)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(vararg option: Option)

    @Delete
    fun delete(vararg option: Option)

    @Query("SELECT * from options where id=:optionId")
    fun getOptionByOptionId(optionId: String): Option

    @Query("SELECT * from options where fId=:facilityId")
    fun getOptionByFacilityId(facilityId: String): List<Option>
}
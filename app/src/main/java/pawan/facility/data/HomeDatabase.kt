package pawan.facility.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pawan.facility.data.dao.ExclusionsDao
import pawan.facility.data.dao.FacilityDao
import pawan.facility.data.dao.OptionDao
import pawan.facility.data.model.Exclusion
import pawan.facility.data.model.Facility
import pawan.facility.data.model.Option

/**
 * Created by Pawan Pal on 19/5/19.
 */

@Database(
    entities = [Facility::class, Exclusion::class, Option::class],
    version = 1
)
abstract class HomeDatabase : RoomDatabase() {

    abstract fun facilityDao(): FacilityDao
    abstract fun optionDao(): OptionDao
    abstract fun exclusionsDao(): ExclusionsDao

    companion object {
        private var INSTANCE: HomeDatabase? = null
        fun getDatabase(context: Context): HomeDatabase? {
            if (INSTANCE == null) {
                synchronized(HomeDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE =
                            Room.databaseBuilder(context.applicationContext, HomeDatabase::class.java, "home_database")
                                .allowMainThreadQueries()
                                .build()
                    }
                }
            }
            return INSTANCE
        }
    }

    fun deleteAll(context: Context) {
        val db = getDatabase(context)
        db?.clearAllTables()
    }
}
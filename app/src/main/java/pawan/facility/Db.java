package pawan.facility;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import pawan.facility.data.dao.ExclusionsDao;
import pawan.facility.data.dao.FacilityDao;
import pawan.facility.data.dao.OptionDao;
import pawan.facility.data.model.Exclusion;
import pawan.facility.data.model.Facility;
import pawan.facility.data.model.Option;

/**
 * Created by Pawan Pal on 19/5/19.
 */
@Database(entities = {Facility.class, Exclusion.class, Option.class}, version = 2)
public abstract class Db extends RoomDatabase {
    public abstract FacilityDao facilityDao();

    public abstract OptionDao optionDao();

    public abstract ExclusionsDao exclusionsDao();

    private static Db INSTANCE;

    public static Db getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (Db.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context,
                            Db.class, "survey_database")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

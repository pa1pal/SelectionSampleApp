package pawan.facility.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Pawan Pal on 19/5/19.
 */
@Entity(tableName = "facilities")
public class Facility {

    @SerializedName("facility_id")
    @Expose
    @PrimaryKey
    @NonNull
    private String facilityId;
    @SerializedName("name")
    @Expose
    private String name;

    @Ignore
    @SerializedName("options")
    @Expose
    private List<Option> options = null;

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }
}

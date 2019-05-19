package pawan.facility.data

import android.content.Context
import androidx.lifecycle.LiveData
import pawan.facility.data.dao.ExclusionsDao
import pawan.facility.data.dao.FacilityDao
import pawan.facility.data.dao.OptionDao
import pawan.facility.data.model.Facility
import pawan.facility.data.model.Home
import pawan.facility.data.model.Option
import pawan.facility.network.RetrofitManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Pawan Pal on 19/5/19.
 */
class DataManager(context: Context?) {
    private var homeDatabase: HomeDatabase = HomeDatabase.getDatabase(context!!)!!
    private var facilityDao: FacilityDao
    private var optionDao: OptionDao
    private var exclusionsDao: ExclusionsDao

    init {
        facilityDao = homeDatabase.facilityDao()
        optionDao = homeDatabase.optionDao()
        exclusionsDao = homeDatabase.exclusionsDao()
    }

    fun getDataFromDatabase(): LiveData<List<Facility>> {
        return homeDatabase.facilityDao().getAllFacility()
    }

    fun getDataFromApi() {
        RetrofitManager.SERVICE.getSurveyData().enqueue(object : Callback<Home> {
            override fun onResponse(call: Call<Home>, response: Response<Home>) {
                populateDatabase(response.body())
            }
            override fun onFailure(call: Call<Home>, t: Throwable) {

            }
        })
    }

    fun populateDatabase(data: Home?) {
        for (fac in data?.facilities!!) {
            facilityDao.insert(fac)

            for (opt in fac.options) {
                var option: Option = opt
                option.fId = fac.facilityId
                optionDao.insert(option)
            }
        }

        for (exlList in data.exclusions) {
            for (exclusionData in exlList.listIterator()) {
                exclusionsDao.insert(exclusionData)
            }
        }
    }

}
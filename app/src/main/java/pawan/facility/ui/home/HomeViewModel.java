package pawan.facility.ui.home;

import android.app.Application;
import android.app.ListActivity;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import pawan.facility.data.DataManager;
import pawan.facility.data.model.Facility;
import pawan.facility.data.model.Home;
import pawan.facility.data.model.Option;
import pawan.facility.network.RetrofitManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

/**
 * Created by Pawan Pal on 19/5/19.
 */
public class HomeViewModel extends AndroidViewModel {
    MutableLiveData<Home> homeData = new MutableLiveData<>();
    LiveData<List<Facility>> facilityData = new MutableLiveData<>();
    MutableLiveData<Option> optionData = new MutableLiveData<>();
    DataManager dataManager;


    public HomeViewModel(@NonNull Application application) {
        super(application);
        dataManager = new DataManager(application);
        facilityData = dataManager.getDataFromDatabase();
    }


    void getData() {
        RetrofitManager.SERVICE.getSurveyData().enqueue(new Callback<Home>() {
            @Override
            public void onResponse(Call<Home> call, Response<Home> response) {
                homeData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Home> call, Throwable t) {

            }
        });
    }
}

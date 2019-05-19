package pawan.facility.job;

import androidx.annotation.NonNull;
import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;
import pawan.facility.data.DataManager;

import java.util.concurrent.TimeUnit;

/**
 * Created by Pawan Pal on 19/5/19.
 */
public class GetDataFromApiJob extends Job {
    private static final String TAG = "Job";
    private DataManager dataManager;

    @NonNull
    @Override
    protected Result onRunJob(@NonNull Params params) {
        dataManager = new DataManager(getContext());
        dataManager.getDataFromApi();
        return Result.SUCCESS;
    }

    public static void scheduleJob() {
        new JobRequest.Builder(GetDataFromApiJob.TAG)
                .setExecutionWindow(TimeUnit.DAYS.toMillis(1), TimeUnit.DAYS.toMillis(3))
                .build()
                .schedule();
    }

    public static void startJobNow() {
        new JobRequest.Builder(GetDataFromApiJob.TAG)
                .startNow()
                .build()
                .schedule();
    }

}

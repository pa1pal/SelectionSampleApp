package pawan.facility.job;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;

/**
 * Created by Pawan Pal on 19/5/19.
 */
public class JobCreater implements JobCreator {
    @Nullable
    @Override
    public Job create(@NonNull String tag) {
        return new  GetDataFromApiJob();
    }
}

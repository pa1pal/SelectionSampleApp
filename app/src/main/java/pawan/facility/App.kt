package pawan.facility

import android.app.Application
import com.evernote.android.job.JobCreator
import com.facebook.stetho.Stetho
import com.evernote.android.job.JobManager
import pawan.facility.job.JobCreater


/**
 * Created by Pawan Pal on 19/5/19.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        JobManager.create(this).addJobCreator(JobCreater())

    }
}
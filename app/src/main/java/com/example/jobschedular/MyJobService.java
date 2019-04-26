package com.example.jobschedular;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

public class MyJobService extends JobService {

    public static final String TAG ="JObSc";
    private boolean jobCancelled = false;

    @Override
    public boolean onStartJob(final JobParameters params) {

        Log.d(TAG,"Job Started");
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    Log.d(TAG, "run" + i);
                    if (jobCancelled) {
                        return;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Log.d(TAG, "Job Finished");
                jobFinished(params, false);
            }
        }).start();

        return true;
    }


    @Override
    public boolean onStopJob(JobParameters params) {

        Log.d(TAG,"Job Cancelled before completion");
        jobCancelled =true;

        return true;
    }
}

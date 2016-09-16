package com.apnatutorials.model;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.apnatutorials.asynctaskexample.R;

import java.util.Objects;

public class AsyncTaskHandler extends android.os.AsyncTask<String, String, String> {
    private AsyncCommunicator communicator = null;

    public AsyncTaskHandler(AsyncCommunicator communicator) {
        this.communicator = communicator;
    }

    @Override
    protected void onPostExecute(String objects) {
        communicator.onProcessResult(objects);

    }

    @Override
    protected String doInBackground(String... params) {
        publishProgress(communicator.getStringById(R.string.sleep_start_now_for) + " " + params[0] + " " + communicator.getStringById(R.string.mili_seconds));
        String result = "";
        Log.v("doInBackground", params[0]);

        try {
            Thread.sleep(Integer.parseInt(params[0]));
            result = communicator.getStringById(R.string.slept) + " " + params[0].toString() + " " + communicator.getStringById(R.string.mili_seconds);

        } catch (Exception e) {
            e.printStackTrace();
            publishProgress(communicator.getStringById(R.string.operation_failed) + " " + e.getMessage());
        }
        return result;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(String... values) {
        communicator.onProgressUpdate(values[0]);


    }
}

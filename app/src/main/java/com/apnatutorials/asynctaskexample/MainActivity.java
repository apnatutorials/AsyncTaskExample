package com.apnatutorials.asynctaskexample;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.apnatutorials.model.AsyncCommunicator;
import com.apnatutorials.model.AsyncTaskHandler;

public class MainActivity extends AppCompatActivity implements AsyncCommunicator {
    EditText etSleepTime;
    TextView tvProgressStatus;
    Button btnSleep;
    ProgressDialog pDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etSleepTime = (EditText) findViewById(R.id.etSleepTime);
        tvProgressStatus = (TextView) findViewById(R.id.tvProgressStatus);
    }

    public void initiateAsyncTask(View view) {
        AsyncTaskHandler task = new AsyncTaskHandler(this);
        task.execute(new String[]{etSleepTime.getText().toString()} );
        tvProgressStatus.setText( getString( R.string.going_to_sleep_for ) + " "  + etSleepTime.getText().toString() + " " + getString(  R.string.mili_seconds ) );
        pDialog = new ProgressDialog(this);
        pDialog.setTitle(R.string.async_title);
        pDialog.setMessage( getString( R.string.async_message ));

        pDialog.setIndeterminate(true);
        pDialog.show();
    }

    @Override
    public void onProgressUpdate(String progress) {
        tvProgressStatus.setText(progress);
    }

    @Override
    public void onProcessResult(String result) {
        tvProgressStatus.setText(result);
        pDialog.cancel();

    }

    @Override
    public String getStringById(int id) {
        return getString(id);
    }

}

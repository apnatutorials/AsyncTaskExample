package com.apnatutorials.model;

/**
 * Created by Angel on 8/1/2016.
 */
public interface AsyncCommunicator {
    public void onProgressUpdate(String progress);

    public void onProcessResult(String result);

    public String getStringById(int id);
}

package com.krisnadwia.ipaddressfinder;

import android.os.AsyncTask;

import java.net.InetAddress;

@SuppressWarnings("ALL")
public class NetTask extends AsyncTask<String, Integer, String> {

    @Override
    protected String doInBackground(String... strings) {

        InetAddress inetAddress;

        try {
            inetAddress = InetAddress.getByName(strings[0]);
            return inetAddress.getHostAddress(); // returns Host address

        } catch (Exception e) {
            return "E"; // returns error code
        }
    } // end of doInBackground()
} // end of NetStat Class

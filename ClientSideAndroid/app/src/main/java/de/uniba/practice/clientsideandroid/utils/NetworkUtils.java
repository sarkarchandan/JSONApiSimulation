package de.uniba.practice.clientsideandroid.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import de.uniba.practice.clientsideandroid.R;

/**
 * Created by chandan on 05.05.17.
 */

public class NetworkUtils {
    
    private static final String TAG = NetworkUtils.class.getSimpleName();

    //URL String to query for data
    private static final String SOURCE_URL_STRING = "http://192.168.1.100:8888/server/endpoint/rest/faculties/";

    /**
     * Builds the Url to fetch data according to the type of data.
     * @param typeOfData
     * @return
     */
    public static URL buildDataURL(Context context,String typeOfData){
        URL dataSourceURL = null;
        if(typeOfData == context.getString(R.string.data_type_general)){
            Uri dataSourceEndpointFacultiesUri =
                    Uri.parse(context.getString(R.string.datasource_base_url)).buildUpon().build();
            try {
                dataSourceURL = new URL(dataSourceEndpointFacultiesUri.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }else {
            Uri dataSourceEndpointCoursesUri = Uri.parse(context.getString(R.string.datasource_base_url)).buildUpon()
                    .appendPath(typeOfData).build();
            try {
                dataSourceURL = new URL(dataSourceEndpointCoursesUri.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return dataSourceURL;
    }

    /**
     * Fetches the JSON Data in background thread.
     * @param movieDataUrl
     * @return
     * @throws IOException
     */
    public static String fetchMovieDataFromHttpUrl(URL movieDataUrl) throws IOException {
        Log.d(TAG,"fetchMovieDataFromHttpUrl() called");
        URL test = new URL(SOURCE_URL_STRING);
        HttpURLConnection httpURLConnection = (HttpURLConnection) test.openConnection();
        try{
            InputStream inputStream = httpURLConnection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            if(hasInput){
                String result = scanner.next();
                Log.d(TAG,"Scanner has got: "+result);
                return result;
            }else{
                Log.d(TAG,"Scanner has found nothing.");
                return null;
            }
        }finally {
            httpURLConnection.disconnect();
        }
    }

    /**
     * Method checkConnectivityStatus checks the connectivity status of the device and helps the app to act reasonably.
     * We don' want our app to crash when the NetworkConnectivity is not available.
     * @param context //Context of the Activity class that checks the Connection status.
     * @return boolean // indicating the connectivity status of the device.
     */
    private static boolean checkConnectivityStatus(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isConnected()){
            try {
                URL url = new URL("http://www.google.com");
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setConnectTimeout(3000);
                httpURLConnection.connect();
                if(httpURLConnection.getResponseCode()==HttpURLConnection.HTTP_OK){
                    return new Boolean(true);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Checks the network Status in Background threads and returns boolean. This is a convenient method to be used all over
     * the app.
     * @param context
     * @return
     */
    public static boolean checkConnectionStatusInBackground(final Context context){

        boolean deviceNetworkStatus = false;

        //Defining an background task to check the network status.
        AsyncTask connectivityCheckAsyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                boolean networkStatus = checkConnectivityStatus(context);
                return new Boolean(networkStatus);
            }
        };

        //Checking network status.
        try {
            deviceNetworkStatus = (boolean) connectivityCheckAsyncTask.execute().get();
            Log.d(TAG, "Current Network Status: "+deviceNetworkStatus);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return deviceNetworkStatus;
    }
}

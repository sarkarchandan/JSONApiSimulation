package de.uniba.practice.clientsideandroid.ui;

import android.content.Context;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

import de.uniba.practice.clientsideandroid.R;
import de.uniba.practice.clientsideandroid.utils.NetworkUtils;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{

    private static final String TAG = MainActivity.class.getSimpleName();

    //Defining the id for the AsyncTask Loader.
    private static final int MAIN_LOADER_ID = 1234;

    private static final String DATASOURCE_SEARCH_KEY = "data_source_search_key";

    private TextView textView_JSONData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_JSONData = (TextView) findViewById(R.id.textView_JSONData);

        getSupportLoaderManager().initLoader(MAIN_LOADER_ID,null,this);
        loadData(this);
    }

    /**
     *
     * @param context
     */
    public void loadData(Context context){
        Bundle dataSourceSearchBundle = new Bundle();
        dataSourceSearchBundle.putString(DATASOURCE_SEARCH_KEY,context.getString(R.string.datasource_base_url));
        LoaderManager loaderManager = getSupportLoaderManager();
        Loader<String> searchLoader = loaderManager.getLoader(MAIN_LOADER_ID);

        if(searchLoader == null){
            loaderManager.initLoader(MAIN_LOADER_ID,dataSourceSearchBundle,this);
        }else {
            loaderManager.restartLoader(MAIN_LOADER_ID,dataSourceSearchBundle,this);
        }
    }



    @Override
    public Loader<String> onCreateLoader(int loaderId, final Bundle bundle) {
        return new AsyncTaskLoader<String>(this) {
            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                if(bundle == null){
                    return ;
                }
                forceLoad();
            }

            @Override
            public String loadInBackground() {
                String typeOfData;
                String jsonData = null;
                if(bundle == null){
                    return null;
                }else{
                    typeOfData = bundle.getString(DATASOURCE_SEARCH_KEY);
                }
                try {
                    jsonData = NetworkUtils.fetchMovieDataFromHttpUrl(NetworkUtils.buildDataURL(getApplicationContext(),typeOfData));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return jsonData;
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        Log.d(TAG,"AsyncTaskLoader has found: "+data);
        if(data == null || data.equals("")){
            textView_JSONData.setText("No Data");
        }else {
            textView_JSONData.setText(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}

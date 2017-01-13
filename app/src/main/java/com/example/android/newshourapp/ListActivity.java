package com.example.android.newshourapp;

import android.app.LoaderManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class ListActivity  extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<CustomObject>> {

    private static final String LOG_TAG = ListActivity.class.getName();


    private String REQUEST_URL;

    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int NEWS_LOADER_ID = 1;

    /** Adapter for the list of earthquakes */
    private CustomAdapter mAdapter;

    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Bundle bundle = getIntent().getExtras();
        String url = bundle.getString("urltopass");
        REQUEST_URL = url;
        final ListView NewsListView = (ListView) findViewById(R.id.list);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        NewsListView.setEmptyView(mEmptyStateTextView);

        // Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new CustomAdapter(this, new ArrayList<CustomObject>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        NewsListView.setAdapter(mAdapter);
        NewsListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CustomObject customObject = mAdapter.getItem(i);
                final String url = customObject.getUrl();
                if(url==null){
                    AlertDialog alertDialog = new AlertDialog.Builder(ListActivity.this).create();
                    alertDialog.setTitle("No Data Found");
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Dismiss",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
                else {
                AlertDialog alertDialog = new AlertDialog.Builder(ListActivity.this).create();
                alertDialog.setTitle(customObject.gettitle());
                alertDialog.setMessage("Preview the book in your Browser");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Preview",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                                        Uri.parse(url));

                                if (intent.resolveActivity(getPackageManager()) != null) {
                                    startActivity(intent);
                                }

                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Dismiss",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
            }
        });

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(NEWS_LOADER_ID, null, this);
    }

    @Override
    public Loader<List<CustomObject>> onCreateLoader(int i, Bundle bundle) {
        return new NewsLoader(this, REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<CustomObject>> loader, List<CustomObject> customObjects) {
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);


        mEmptyStateTextView.setText("No Data Found");

        mAdapter.clear();
        if (customObjects != null && !customObjects.isEmpty()) {
            mAdapter.addAll(customObjects);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<CustomObject>> loader) {
        mAdapter.clear();
    }
}

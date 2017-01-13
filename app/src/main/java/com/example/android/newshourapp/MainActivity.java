package com.example.android.newshourapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String url1= "https://content.guardianapis.com/search?q=";
    private String url3 = "&api-key=test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText editText = (EditText) findViewById(R.id.editText);
                String urlmid = editText.getText().toString().trim();
                String Furlmid = urlmid;
                Log.v("Middle", urlmid);
                if (urlmid.isEmpty()) {
                    editText.setError("Search Term Is Required");
                } else {
                    if (urlmid.contains(" ")) {
                        String temp = urlmid.replaceAll(" ", "%20AND%20");
                        Furlmid = temp;
                    }
                    if (isNetworkAvailable()) {
                        String URL_ID = url1+Furlmid+url3;
                        Log.v("URL", URL_ID);
                        //TextView textView = (TextView)findViewById(R.id.textView);
                       //textView.setText(URL_ID);
                        Intent intent = new Intent(MainActivity.this, ListActivity.class);
                        intent.putExtra("urltopass", URL_ID);
                        startActivity(intent);}
                    else {
                        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                        alertDialog.setTitle("No Internet Connection Available");
                        alertDialog.setMessage("We can not proceed further there is no Internet Conection");
                        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Dismiss",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    }
                }

            }
        });

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

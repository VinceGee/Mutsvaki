package com.empire.vince.mutsvaki.addingtomysql;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View.OnClickListener;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.empire.vince.mutsvaki.JSONParser;
import com.empire.vince.mutsvaki.R;

/*
 * Created by VinceGee on 03/16/2016.
 */

public class AddNewIdiom extends Activity implements OnClickListener {

    // Progress Dialog
    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();
    private EditText txtnewidiom;
    private EditText txtmeaning;
    private Button btnsavenew;
    private int success;//to determine JSON signal insert success/fail

    // url to insert new idiom (change accordingly)
    private static String url_insert_new = "http://10.41.100.190/merchant/insertnew.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_idiom);

        // Edit Text
        txtnewidiom = (EditText) findViewById(R.id.txtnewidiom);
        txtmeaning = (EditText) findViewById(R.id.txtmeaning);

        // Save button
        btnsavenew = (Button) findViewById(R.id.btnsavenew);
        // button click event
        btnsavenew.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btnsavenew){
            //call the InsertNewIdiom thread
            new InsertNewIdiom().execute();
            /*if (success==1){

            }else{

            }*/
        }

    }

/**
     * Background Async Task to Create new Idioms
     * */

    class InsertNewIdiom extends AsyncTask<String, String, String> {
        //capture values from EditText
        String entry = txtnewidiom.getText().toString();
        String meaning = txtmeaning.getText().toString();

/**
         * Before starting background thread Show Progress Dialog
         * */

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(AddNewIdiom.this);
            pDialog.setMessage("Saving the new IDIOM ("+entry+")...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

/**
         * Inserting the new idiom
         * */

        protected String doInBackground(String... args) {


            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("entry", entry));
            params.add(new BasicNameValuePair("meaning", meaning));

            // getting JSON Object
            // Note that create product url accepts GET method
            JSONObject json = jsonParser.makeHttpRequest(url_insert_new, "POST", params);

            // check log cat from response
            //Log.d("Insert New Idiom Response", json.toString());

            // check for success tag
            try {
                success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully save new idiom
                    Toast.makeText(getApplicationContext(), "New idiom saved...", Toast.LENGTH_LONG).show();
                } else {
                    // failed to add new idiom
                    Toast.makeText(getApplicationContext(), "New idiom FAILED to saved...", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

/**
         * After completing background task Dismiss the progress dialog
         * **/

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }

    }
}

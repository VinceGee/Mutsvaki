package com.empire.vince.mutsvaki;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.empire.vince.mutsvaki.addingtomysql.AddNewIdiom;

/**
 * Created by VinceGee on 03/16/2016.
 */
public class Search extends Activity implements View.OnClickListener {
    private EditText txtkeyword;
    private Button btnsearch;
    private Button addidiom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //link to UI
        txtkeyword=(EditText)findViewById(R.id.txtkeyword);
        btnsearch=(Button)findViewById(R.id.btnsearch);
        btnsearch.setOnClickListener(this);
        addidiom=(Button)findViewById(R.id.addidiom);
        addidiom.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnsearch){
            Intent searchIntent = new Intent(this, ListResult.class);
            //send the keyword to the next screen
            searchIntent.putExtra("keyword",txtkeyword.getText().toString());
            //call the screen for listing
            startActivity(searchIntent);
        }else if (v.getId()==R.id.addidiom){
            Intent add = new Intent(getApplicationContext(), AddNewIdiom.class);
            startActivity(add);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search, menu);
        return true;
    }

}


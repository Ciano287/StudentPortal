package com.example.studentportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    LinearLayout.LayoutParams layoutParams;
    LinearLayout linearLayout;
    static String URL_NAME;
    TextView textViewGoogle;
    TextView textViewVLO;
    TextView textViewRooster;
    TextView textViewDMCI;
    Intent intent;
    String url;
    String title;
    boolean checkC;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        final String GOOGLE = getString(R.string.google_url);
        final String VLO = getString(R.string.vlo_url);
        final String ROOSTER = getString(R.string.rooster_url);
        final String DMCI = getString(R.string.dmci_url);


        URL_NAME = "com.example.studentportal.MainActivity.url_name";
        setSupportActionBar(toolbar);

        textViewDMCI = findViewById(R.id.textViewDMCI);
        textViewRooster = findViewById(R.id.textViewRooster);
        textViewVLO = findViewById(R.id.textViewVLO);

        textViewVLO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                intent.putExtra(URL_NAME, VLO);
                startActivity(intent);
            }
        });
        textViewDMCI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                intent.putExtra(URL_NAME, DMCI);
                startActivity(intent);
            }
        });
        textViewRooster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                intent.putExtra(URL_NAME, ROOSTER);
                startActivity(intent);
            }
        });



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, AddPortal.class);
            startActivityForResult(intent,1);
                startActivity(intent);

            }
        });




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if(resultCode == RESULT_OK ){
                assert data != null;
                final String title = data.getStringExtra(AddPortal.TITLE);
                final String url = data.getStringExtra(AddPortal.URL);
                final int WIDTH = 300;
                final int HEIGHT = 300;

                TextView mtextView = new TextView(this);
                linearLayout = findViewById(R.id.linearLayout);

                mtextView.setLayoutParams(new LinearLayout.LayoutParams(WIDTH,HEIGHT));
                mtextView.setText(title);
                mtextView.setTextSize(20);
                linearLayout.addView(mtextView);




                mtextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                        intent.putExtra(URL_NAME, url);
                        startActivity(intent);
                    }
                });
            }
        }
    }
}

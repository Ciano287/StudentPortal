package com.example.studentportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static String URL_NAME = "com.example.studentportal.MainActivity.url_name";
    Toolbar toolbar;
    PortalAdapter mAdapter;
    List<PortalObject> mPortalList;
    RecyclerView mRecyclerView;
    GridLayoutManager linearLayoutManager;
    GestureDetector mGestureDetector;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        final String VLO = getString(R.string.vlo_url);
        final String ROOSTER = getString(R.string.rooster_url);
        final String DMCI = getString(R.string.dmci_url);
        mPortalList = new ArrayList<>();
        mAdapter = new PortalAdapter(mPortalList);
        mRecyclerView = findViewById(R.id.rvPortal);
        mRecyclerView.setAdapter(mAdapter);

        linearLayoutManager = new GridLayoutManager(this,3);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        setSupportActionBar(toolbar);


        mPortalList.add(new PortalObject("Rooster",ROOSTER));
        mPortalList.add(new PortalObject("VLO",VLO));
        mPortalList.add(new PortalObject("DMCI",DMCI));



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, AddPortal.class);
                startActivityForResult(intent, 1);
                startActivity(intent);

            }
        });

        mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                int mAdapterPosition = recyclerView.getChildAdapterPosition(child);

                if(child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                    Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                    intent.putExtra(URL_NAME, mPortalList.get(mAdapterPosition).getUrl());
                    startActivity(intent);
            }
            return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {

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
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                assert data != null;
                final String title = data.getStringExtra(AddPortal.TITLE);
                final String url = data.getStringExtra(AddPortal.URL);

                PortalObject portalObject = new PortalObject(title,url);

                mPortalList.add(portalObject);
                mAdapter.notifyDataSetChanged();
                updateUI();

            }
        }
    }
    private void updateUI() {
        if (mAdapter == null) {
            mAdapter = new PortalAdapter(mPortalList);
            mRecyclerView.setAdapter(mAdapter);
        }
        else {
            mAdapter.swapList(mPortalList);
        }}
}

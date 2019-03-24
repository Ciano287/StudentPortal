package com.example.studentportal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddPortal extends AppCompatActivity {

    TextView inputUrl;
    TextView inputTitle;
    Button addPortalButton;
    static final String TITLE = "com.example.studentportal.title";
    static String URL = "com.example.studentportal.url";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_portal);
        inputUrl = findViewById(R.id.inputUrl);
        inputTitle = findViewById(R.id.inputTitle);
        addPortalButton = findViewById(R.id.addPortalButton);

        addPortalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddPortal.this, MainActivity.class);
                String text = inputTitle.getText().toString();
                String url = inputUrl.getText().toString();
                intent.putExtra(TITLE, text);
                intent.putExtra(URL, url);
                setResult(RESULT_OK, intent);
                finish();


            }
        });

    }


}

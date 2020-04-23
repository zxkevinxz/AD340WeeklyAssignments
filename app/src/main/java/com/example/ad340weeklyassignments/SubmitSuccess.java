package com.example.ad340weeklyassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SubmitSuccess extends AppCompatActivity {

    private static final String TAG = SubmitSuccess.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_success);

        TextView textView = findViewById(R.id.textView);
        Intent intent = getIntent();
        Bundle userInfo = intent.getExtras();

        StringBuilder msg = new StringBuilder("Thanks for signing up, ");

        String username = userInfo.getString(Constants.KEY_USERNAME);

        msg.append(username).append("!");

        textView.setText(msg);

    }
}

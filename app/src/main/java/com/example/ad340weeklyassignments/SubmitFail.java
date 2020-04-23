package com.example.ad340weeklyassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SubmitFail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_fail);
        TextView failMsg = findViewById(R.id.failMsg);
        Intent intent = getIntent();

        String msg = intent.getStringExtra(Constants.FAIL_MSGS);

        failMsg.setText(msg);
    }
}

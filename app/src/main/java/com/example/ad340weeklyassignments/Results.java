package com.example.ad340weeklyassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Results extends AppCompatActivity {

    private static final String TAG = Results.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        TextView results = findViewById(R.id.results);
        Intent intent = getIntent();

        results.setText(intent.getStringExtra("submitResults"));

    }

    public void goToMain(View view) {
        Intent intent = new Intent(Results.this, MainActivity.class);
        startActivity(intent);
    }
}

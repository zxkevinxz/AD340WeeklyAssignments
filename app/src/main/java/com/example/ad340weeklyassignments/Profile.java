package com.example.ad340weeklyassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    private static final String TAG = Profile.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        Bundle userInfo = intent.getExtras();

        TextView profileFirstName = findViewById(R.id.profileFirstName);
        TextView profileLastName = findViewById(R.id.profileLastName);
        TextView age = findViewById(R.id.age);
        TextView profileOccupation = findViewById(R.id.profileOccupation);
        TextView profileDescription = findViewById(R.id.profileDescription);

        profileFirstName.setText(userInfo.getString(Constants.KEY_FIRSTNAME));
        profileLastName.setText(userInfo.getString(Constants.KEY_LASTNAME));
        age.setText(Integer.toString(userInfo.getInt(Constants.KEY_AGE)));
        profileOccupation.setText(userInfo.getString(Constants.KEY_OCCUPATION));
        profileDescription.setText(userInfo.getString(Constants.KEY_DESCRIPTION));

    }

}

package com.example.ad340weeklyassignments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.media.audiofx.DynamicsProcessing;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    EditText name;
    EditText email;
    EditText username;
    EditText age;
    TextView dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        username = (EditText) findViewById(R.id.username);
        age = (EditText) findViewById(R.id.age);
        dob = (TextView) findViewById(R.id.dob);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        TextView dob = (TextView) findViewById(R.id.dob);
        StringBuilder date = new StringBuilder();
        month = month + 1;
        date.append(month).append('/').append(dayOfMonth).append('/').append(year);
        dob.setText(date);
    }

    public void onSubmit(View view) {
        String testName = name.getText().toString();
        String testEmail = email.getText().toString();
        String testUsername = username.getText().toString();
        int testAge = Integer.parseInt(age.getText().toString());
        String bday = dob.getText().toString();

        ArrayList<String> errors = new ArrayList<>();

        if (testName.equals(""))
            errors.add("name");
        if (testUsername.equals(""))
            errors.add("username");
        if (testAge < 18)
            errors.add("age");
        if (!testEmail.matches("^(.+)@(.+)$"))
            errors.add("email");


        if (errors.size() > 0)
            goToFail(errors);

    }

    public void goToSuccess() {
        Intent intent = new Intent(MainActivity.this, SubmitSuccess.class);
        Bundle userInfo = new Bundle();
        userInfo.putString(Constants.KEY_NAME, name.getText().toString());
        userInfo.putString(Constants.KEY_EMAIL, email.getText().toString());
        userInfo.putString(Constants.KEY_USERNAME, username.getText().toString());
        userInfo.putString(Constants.KEY_AGE, age.getText().toString());
        userInfo.putString(Constants.KEY_DOB, dob.getText().toString());
        intent.putExtras(userInfo);
        startActivity(intent);
    }

    public void goToFail(ArrayList<String> errors) {

    }
}

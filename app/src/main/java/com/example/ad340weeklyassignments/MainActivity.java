package com.example.ad340weeklyassignments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.Period;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText name;
    private EditText email;
    private EditText username;
    private TextView dob;
    private TextView err;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        username = (EditText) findViewById(R.id.username);
        dob = (TextView) findViewById(R.id.dob);
        err = (TextView) findViewById(R.id.errorsMsg);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        name.setText(R.string.empty);
        email.setText(R.string.empty);
        username.setText(R.string.empty);
        dob.setText(Constants.DEFAULT_DOB);
        err.setText(R.string.empty);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.i(TAG, "onSaveInstanceState: ");
        super.onSaveInstanceState(outState);
        outState.putString(Constants.KEY_DOB, dob.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey(Constants.KEY_DOB))
            dob.setText(savedInstanceState.getString(Constants.KEY_DOB));
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

        err.setText(R.string.empty);

        String testName = name.getText().toString();
        String testEmail = email.getText().toString();
        String testUsername = username.getText().toString();
        String bday = dob.getText().toString();

        StringBuilder errors = new StringBuilder();

        if (testName.equals(""))
            errors.append(getString(R.string.ERR_NAME));
        if (testUsername.equals(""))
            errors.append(getString(R.string.ERR_USERNAME));
        if (!testEmail.matches("^(.+)@(.+)$"))
            errors.append(getString(R.string.ERR_EMAIL));
        if (bday.equals(Constants.DEFAULT_DOB))
            errors.append(getString(R.string.ERR_NO_DOB));
        else if (checkDOB(bday) < 18)
            errors.append(getString(R.string.ERR_DOB));

        if (errors.toString().equals(""))
            goToResults();
        else
            err.setText(errors.toString());

    }

    private void goToResults() {

        StringBuilder msg = new StringBuilder();
        msg.append(getString(R.string.THANKS)).append(" ").append(username.getText().toString()).append("!");

        Intent intent = new Intent(MainActivity.this, Results.class);
        intent.putExtra(Constants.KEY_RESULTS, msg.toString());
        startActivity(intent);
    }

    private int checkDOB(String dateOfBirth) {
        int year = Integer.parseInt(dateOfBirth.substring(dateOfBirth.lastIndexOf('/')+1));
        int month = Integer.parseInt(dateOfBirth.substring(0, dateOfBirth.indexOf('/')));
        int day = Integer.parseInt(dateOfBirth.substring(dateOfBirth.indexOf('/')+1, dateOfBirth.lastIndexOf('/')));

        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(year, month, day);

        Period p = Period.between(birthday, today);

        return p.getYears();
    }
}

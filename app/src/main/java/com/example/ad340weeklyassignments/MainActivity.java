package com.example.ad340weeklyassignments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    EditText name;
    EditText email;
    EditText username;
    TextView dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        username = (EditText) findViewById(R.id.username);
        dob = (TextView) findViewById(R.id.dob);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
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
        String bday = dob.getText().toString();

        ArrayList<String> errors = new ArrayList<>();

        if (testName.equals(""))
            errors.add(Constants.KEY_NAME);
        if (testUsername.equals(""))
            errors.add(Constants.KEY_USERNAME);
        if (!testEmail.matches("^(.+)@(.+)$"))
            errors.add(Constants.KEY_EMAIL);
        if (bday.equals(Constants.DEFAULT_DOB))
            errors.add(Constants.NO_DOB);
        else if (checkDOB(bday) < 18)
            errors.add(Constants.KEY_DOB);


        if (errors.size() > 0)
            goToFail(errors);
        else
            goToSuccess();

    }

    private void goToSuccess() {
        Intent intent = new Intent(MainActivity.this, SubmitSuccess.class);
        Bundle userInfo = new Bundle();
        userInfo.putString(Constants.KEY_NAME, name.getText().toString());
        userInfo.putString(Constants.KEY_EMAIL, email.getText().toString());
        userInfo.putString(Constants.KEY_USERNAME, username.getText().toString());
        userInfo.putString(Constants.KEY_DOB, dob.getText().toString());
        intent.putExtras(userInfo);
        startActivity(intent);
    }

    private void goToFail(ArrayList<String> errors) {
        StringBuilder msg = new StringBuilder();
        if (errors.contains(Constants.KEY_NAME))
            msg.append(Constants.ERR_NAME).append("\n");
        if (errors.contains(Constants.KEY_USERNAME))
            msg.append(Constants.ERR_USERNAME).append("\n");;
        if (errors.contains(Constants.KEY_EMAIL))
            msg.append(Constants.ERR_EMAIL).append("\n");;
        if (errors.contains(Constants.NO_DOB))
            msg.append(Constants.ERR_NO_DOB).append("\n");;
        if (errors.contains(Constants.KEY_DOB))
            msg.append(Constants.ERR_DOB).append("\n");;

        Intent intent = new Intent(MainActivity.this, SubmitFail.class);
        intent.putExtra(Constants.FAIL_MSGS, msg.toString());
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

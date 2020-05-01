package com.example.ad340weeklyassignments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.util.Patterns;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.Period;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText username;
    private EditText occupation;
    private EditText description;
    private TextView dob;
    private TextView err;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        occupation = findViewById(R.id.occupation);
        description = findViewById(R.id.description);
        dob = findViewById(R.id.dob);
        err = findViewById(R.id.errorsMsg);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        firstName.setText(getString(R.string.empty));
        lastName.setText("");
        email.setText(getString(R.string.empty));
        username.setText(getString(R.string.empty));
        occupation.setText("");
        description.setText("");
        dob.setText(getString(R.string.empty));
        err.setText(getString(R.string.empty));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (!dob.getText().toString().equals(getString(R.string.empty_dob)))
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

        String testFirstName = firstName.getText().toString();
        String testLastName = lastName.getText().toString();
        String testEmail = email.getText().toString();
        String testUsername = username.getText().toString();
        String testOccupation = occupation.getText().toString();
        String testDescription = description.getText().toString();
        String bday = dob.getText().toString();

        StringBuilder errors = new StringBuilder();

        if (testFirstName.equals(""))
            errors.append(getString(R.string.ERR_FIRST_NAME));
        if (testLastName.equals(""))
            errors.append(getString(R.string.ERR_LAST_NAME));
        if (testFirstName.length() > 25 || testLastName.length() > 25)
            errors.append(getString(R.string.ERR_NAME_LENGTH));
        if (testUsername.equals(""))
            errors.append(getString(R.string.ERR_USERNAME));
        if (!checkEmail(testEmail))
            errors.append(getString(R.string.ERR_EMAIL));
        if(testOccupation.equals(""))
            errors.append(getString(R.string.ERR_OCCUPATION));
        if(testDescription.equals(""))
            errors.append(getString(R.string.ERR_DESCRIPTION));
        if (bday.equals(Constants.DEFAULT_DOB))
            errors.append(getString(R.string.ERR_NO_DOB));
        else if (checkDOB(bday) < 18)
            errors.append(getString(R.string.ERR_DOB));

        if (errors.toString().equals(""))
            goToProfile(testFirstName, testLastName, testUsername, testEmail, testOccupation, testDescription, bday, checkDOB(bday));
        else
            err.setText(errors.toString());

    }

    private void goToProfile(String fName, String lName, String uName, String email,
                             String occ, String desc, String birthday, int age) {

        Bundle userInfo = new Bundle();

        userInfo.putString(Constants.KEY_FIRSTNAME, fName);
        userInfo.putString(Constants.KEY_LASTNAME, lName);
        userInfo.putString(Constants.KEY_USERNAME, uName);
        userInfo.putString(Constants.KEY_EMAIL, email);
        userInfo.putString(Constants.KEY_OCCUPATION, occ);
        userInfo.putString(Constants.KEY_DESCRIPTION, desc);
        userInfo.putString(Constants.KEY_DOB, birthday);
        userInfo.putInt(Constants.KEY_AGE, age);

        Intent intent = new Intent(MainActivity.this, Profile.class);
        intent.putExtras(userInfo);
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

    private boolean checkEmail(String email) {
        return !email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}

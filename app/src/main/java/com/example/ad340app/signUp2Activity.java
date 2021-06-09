package com.example.ad340app;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

public class signUp2Activity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private TextView dateText;
    private TextView showText;
    private Button button;
    String username, email, check, name, job, description, age;
    int years, months, dayOfMonths, date;
    private static final String TAG = MainActivity.class.getSimpleName();

    EditText nameInput;
    EditText emailInput;
    EditText usernameInput;
    EditText jobInput;
    EditText descriptionInput;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up2);
        dateText = findViewById(R.id.date_text);
        button = findViewById(R.id.submitBut);

        nameInput = (EditText) findViewById(R.id.nameInput);
        emailInput = (EditText) findViewById(R.id.emailInput);
        usernameInput = (EditText) findViewById(R.id.userNameInput);
        jobInput = (EditText) findViewById(R.id.jobInput);
        descriptionInput = (EditText) findViewById(R.id.descripInput);

        findViewById(R.id.dialog).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                showDatePickerDailog();
            }
        });
    }

    protected void onRestart() {
        super.onRestart();
        clear();
        Log.i(TAG, "onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.containsKey(Constants.KEY_TEXTVIEW_TEXT)) {
            nameInput.setText(savedInstanceState.getString(Constants.KEY_TEXTVIEW_TEXT));
        }

        if (savedInstanceState.containsKey(Constants.KEY_EMAIL_TEXT)) {
            emailInput.setText(savedInstanceState.getString(Constants.KEY_EMAIL_TEXT));
        }

        if (savedInstanceState.containsKey(Constants.KEY_USER_TEXT)) {
            usernameInput.setText(savedInstanceState.getString(Constants.KEY_USER_TEXT));
        }

        if (savedInstanceState.containsKey(Constants.KEY_JOB_TEXT)) {
            jobInput.setText(savedInstanceState.getString(Constants.KEY_JOB_TEXT));
        }

        if (savedInstanceState.containsKey(Constants.KEY_DESCRIP_TEXT)) {
            descriptionInput.setText(savedInstanceState.getString(Constants.KEY_DESCRIP_TEXT));
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(Constants.KEY_TEXTVIEW_TEXT, nameInput.getText().toString());
        outState.putString(Constants.KEY_EMAIL_TEXT, emailInput.getText().toString());
        outState.putString(Constants.KEY_USER_TEXT, usernameInput.getText().toString());
        outState.putString(Constants.KEY_JOB_TEXT, jobInput.getText().toString());
        outState.putString(Constants.KEY_DESCRIP_TEXT, descriptionInput.getText().toString());
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    private void showDatePickerDailog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                years = Calendar.getInstance().get(Calendar.YEAR),
                months = Calendar.getInstance().get(Calendar.MONTH),
                dayOfMonths = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = 1 + month + "/" + dayOfMonth + "/" + year;
        dateText.setText(date);
        this.years = year;
        this.months = month + 1;
        this.dayOfMonths = dayOfMonth;
    }

    private boolean validateEmail(){
        String emailInputText = emailInput.getText().toString().trim();
        Log.i(TAG, "eamil is: " + emailInputText);
        if(emailInputText.isEmpty()){
            emailInput.setError("Email can't be empty");
            return false;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(emailInputText).matches()){
            emailInput.setError("Email invalid");
            return false;
        }else{
            emailInput.setError(null);
            return true;
        }
    }

    private boolean validName(){
        String nameText = nameInput.getEditableText().toString().trim();
        Log.i(TAG, "name is: " + nameText);
        if (nameText.isEmpty()){
            nameInput.setError("enter your name pls");
            return false;
        }else {
            nameInput.setError(null);
            return true;
        }
    }

    private boolean validDescrip(){
        String descriptionText = descriptionInput.getEditableText().toString().trim();
        Log.i(TAG, "description is: " + descriptionText);
        if (descriptionText.isEmpty()){
            descriptionInput.setError("enter something about yourself pls");
            return false;
        }else {
            descriptionInput.setError(null);
            return true;
        }
    }

    private boolean validJob(){
        String jobText = jobInput.getEditableText().toString().trim();
        Log.i(TAG, "job is: " + jobText);
        if (jobText.isEmpty()){
            jobInput.setError("enter your job pls");
            return false;
        }else {
            jobInput.setError(null);
            return true;
        }
    }

    private void clear(){
        nameInput.setText("");
        emailInput.setText("");
        usernameInput.setText("");
        dateText.setText("");
        jobInput.setText(" ");
        descriptionInput.setText(" ");
    }

    private boolean validUserName(){
        username = usernameInput.getEditableText().toString().trim();
        Log.i(TAG, "username is: " + username);
        if (username.isEmpty()){
            usernameInput.setError("enter your user name pls");
            return false;
        }else if(username.length()>15){
            usernameInput.setError("username too long");
            return false;
        }else{
            usernameInput.setError(null);
            return  true;
        }
    }

    private boolean validateAge() {
        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(years, months, dayOfMonths);
        check = birthday.toString();
        Period p = Period.between(birthday, today);
        this.date =p.getYears();
        Log.i(TAG, "years, months, dayOfMonths: " + years + " ," + months + " ," + dayOfMonths);
        Log.i(TAG, "Birthday is: " + birthday.toString());
        Log.i(TAG, "date is: " + date);
        if (p.getYears() < 18) {
            dateText.setText("you are under 18, so you can't sign up");
            return  false;
        } else if (check.isEmpty()) {
            dateText.setText("enter your brithday");
            return  false;
        } else {
            return true;
        }
    }

    public void goToProfile(View view) {
        boolean isEmailValid = validateEmail();
        boolean isAgeValid = validateAge();
        boolean isNameValid = validName();
        boolean isUserNamvalid = validUserName();
        boolean isJobValid = validJob();
        boolean isDescriptValid = validDescrip();
        if (!isEmailValid | !isAgeValid | !isNameValid | !isUserNamvalid | !isJobValid | !isDescriptValid) {
            return;
        }
        Intent intent = new Intent(signUp2Activity.this, Activity2.class);
        name = nameInput.getText().toString();
        job = jobInput.getText().toString();
        description = descriptionInput.getText().toString();
        age = String.valueOf(date);
        email = emailInput.getText().toString();
        intent.putExtra(Constants.KEY_NAME, name);
        intent.putExtra(Constants.KEY_EMAIL, email);
        intent.putExtra(Constants.KEY_AGE, age);
        intent.putExtra(Constants.KEY_JOB_NAME, job);
        intent.putExtra(Constants.KEY_DESCRIPTION, description);
        startActivity(intent);
    }
}
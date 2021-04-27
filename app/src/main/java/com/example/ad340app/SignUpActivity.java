package com.example.ad340app;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.os.PersistableBundle;
import java.time.Instant;
import java.time.Period;
import java.time.temporal.Temporal;
import java.time.temporal.ChronoUnit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.util.Calendar;

import static android.text.TextUtils.isEmpty;

public class SignUpActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private TextView dateText;
    private TextView showText;
    private Button button;
    String username, check;
    int years, months, dayOfMonths;
    private static final String TAG = MainActivity.class.getSimpleName();

    EditText nameInput;
    EditText emailInput;
    EditText usernameInput;
    EditText edit;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        dateText = findViewById(R.id.date_text);
        button = findViewById(R.id.submitBut);
        edit = findViewById(R.id.editText);

        nameInput = (EditText) findViewById(R.id.nameInput);
        emailInput = (EditText) findViewById(R.id.emailInput);
        usernameInput = (EditText) findViewById(R.id.userNameInput);

        findViewById(R.id.dialog).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                showDatePickerDailog();
            }
        });
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

        if (savedInstanceState.containsKey(Constants.KEY_TEXTVIEW_TEXT)) {
            usernameInput.setText(savedInstanceState.getString(Constants.KEY_USER_TEXT));
        }

        if (savedInstanceState.containsKey(Constants.KEY_TEXTVIEW_TEXT)) {
            dateText.setText(savedInstanceState.getString(Constants.KEY_DATE_TEXT));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(Constants.KEY_TEXTVIEW_TEXT, nameInput.getText().toString());
        outState.putString(Constants.KEY_DATE_TEXT, dateText.getText().toString());
        outState.putString(Constants.KEY_EMAIL_TEXT, emailInput.getText().toString());
        outState.putString(Constants.KEY_USER_TEXT, usernameInput.getText().toString());
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

    public boolean validateInput(){
        check = Integer.toString(years);
        if (check == null){
            edit.setError("enter your brithday");
            return false;
        }else
            return true;
    }

     public boolean validateAge() {
         LocalDate today = LocalDate.now();
         LocalDate birthday = LocalDate.of(years, months, dayOfMonths);
         Period p = Period.between(birthday, today);
         Log.i(TAG, "years, months, dayOfMonths: " + years + " ," + months + " ," + dayOfMonths);
         Log.i(TAG, "Birthday is: " + birthday.toString());
         if (p.getYears() < 18) {
             edit.setError("you are under 18, so you can't sign up");
             return  false;
         } else if (p.isZero()) {
             edit.setError("enter your brithday");
             return  false;
         } else {
             edit.setError(null);
             return true;
         }
     }

    public void thankYou(View view) {
        boolean isEmailValid = validateEmail();
        boolean isAgeValid = validateAge();
        boolean isCheck = validateInput();
        if (!isEmailValid | !isAgeValid | !isCheck) {
            return;
        }

        Intent intent = new Intent(SignUpActivity.this,Thankyou.class);
        username = usernameInput.getText().toString();
        intent.putExtra(Constants.KEY_USER_NAME, username);
        startActivity(intent);
    }
}
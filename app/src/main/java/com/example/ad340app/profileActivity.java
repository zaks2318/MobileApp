package com.example.ad340app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class profileActivity extends AppCompatActivity{
    private static final String TAG = profileActivity.class.getSimpleName();
    TextView nameText;
    TextView jobText;
    TextView ageText;
    TextView descripText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        nameText = findViewById(R.id.name);
        jobText = findViewById(R.id.job);
        ageText = findViewById(R.id.age);
        descripText = findViewById(R.id.textView7);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        StringBuilder nameMsg = new StringBuilder("Name : ");
        StringBuilder ageMsg = new StringBuilder("Age : ");
        StringBuilder jobMsg = new StringBuilder("Occupationm : ");
        StringBuilder descripMsg = new StringBuilder("Description : ");

        String name = "Name";
        String age = "age";
        String job = "job";
        String description = "description";

        if (b != null) {

            if (b.containsKey(Constants.KEY_NAME)) {
                name = b.getString(Constants.KEY_NAME);
            }

            if (b.containsKey(Constants.KEY_AGE)) {
                age = b.getString(Constants.KEY_AGE);
            }

            if (b.containsKey(Constants.KEY_DESCRIPTION)) {
                description = b.getString(Constants.KEY_DESCRIPTION);
            }

            if (b.containsKey(Constants.KEY_JOB_NAME)) {
                job = b.getString(Constants.KEY_JOB_NAME);
            }
        }

        nameMsg.append(name);
        ageMsg.append(age).append(" years old");
        jobMsg.append(job);
        descripMsg.append(description);

        nameText.setText(nameMsg);
        ageText.setText(ageMsg);
        jobText.setText(jobMsg);
        descripText.setText(descripMsg);
    }

    public void finish(View view) {
        finish();
    }
}

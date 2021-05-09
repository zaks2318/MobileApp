package com.example.ad340app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class profileFragment extends Fragment {

    private static final String TAG = profileActivity.class.getSimpleName();
    static final String NAME = "name";
    static final String AGE = "age";
    static final String JOB = "job";
    static final String DESCRIP = "description";
    String name, job, description, age;
    private TextView nameText;
    private TextView jobText;
    private TextView ageText;
    private TextView descripText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        StringBuilder nameMsg = new StringBuilder("Name: ");
        StringBuilder ageMsg = new StringBuilder("Age: ");
        StringBuilder jobMsg = new StringBuilder("Job: ");
        StringBuilder descripMsg = new StringBuilder("Description: ");

        name = getArguments().getString(NAME);
        job = getArguments().getString(JOB);
        description = getArguments().getString(DESCRIP);
        age = getArguments().getString(AGE);

        nameMsg.append(name);
        ageMsg.append(age).append(" years old");
        jobMsg.append(job);
        descripMsg.append(description);

        nameText = view.findViewById(R.id.name);
        jobText = view.findViewById(R.id.job);
        ageText = view.findViewById(R.id.age);
        descripText = view.findViewById(R.id.textView7);

        nameText.setText(nameMsg);
        ageText.setText(ageMsg);
        jobText.setText(jobMsg);
        descripText.setText(descripMsg);

        return  view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(Constants.KEY_NAME, getArguments().getString(NAME));
        outState.putString(Constants.KEY_AGE, getArguments().getString(AGE));
        outState.putString(Constants.KEY_JOB_TEXT, getArguments().getString(JOB));
        outState.putString(Constants.KEY_DESCRIP_TEXT, getArguments().getString(DESCRIP));
    }
}
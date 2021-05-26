package com.example.ad340app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.ad340app.ViewModel.SettingViewModel;

import java.util.List;

public class SettingsFragment extends Fragment {

    static final String EMAILTEXT = "email";
    String signEmail;

    public TextView email;
    public EditText Gender;
    public EditText DRtime;
    public EditText MaxAge;
    public EditText MinAge;
    public EditText MDsearch;
    public CheckBox Private;
    public Button update;
    public Button delete;

    private SettingViewModel settingViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        email = view.findViewById(R.id.email);
        Gender = view.findViewById(R.id.genderText);
        DRtime = view.findViewById(R.id.ReminderTimeText);
        MaxAge = view.findViewById(R.id.MaxAgeRange);
        MinAge = view.findViewById(R.id.MinAgeRange);
        MDsearch = view.findViewById(R.id.MaxDistanceText);
        Private = view.findViewById(R.id.checkBoxPrivate);
        update = view.findViewById(R.id.UpdataBut);
        delete = view.findViewById(R.id.DeleteBut);


        settingViewModel = new ViewModelProvider(this).get(SettingViewModel.class);

        // Create the observer which updates the UI.
        final Observer<List<SettingEntity>> getSettingObserver = setting -> {
            if (setting == null || setting.size() <= 0) {
                return;
            }

            SettingEntity settingEntities = setting.get(0);

            if (settingEntities == null) {
                return;
            }

            email.setText(settingEntities.getEmail());
            Gender.setText(settingEntities.getGender());
            DRtime.setText(settingEntities.getDailyReminderTime());
            MaxAge.setText(settingEntities.getInterestedAgeMax());
            MinAge.setText(settingEntities.getInterestedAgeMin());
            Private.setChecked(settingEntities.isPrivateAccount());
            MDsearch.setText(settingEntities.getMaxDistance());

        };

        signEmail = getArguments().getString(EMAILTEXT);
        String[] emails = {signEmail};
        settingViewModel.loadAllByIds(this.getContext(), emails).observe(this.getViewLifecycleOwner(), getSettingObserver);
        return view;
    }

    public void updateDatabase(View view){
        SettingEntity setting = new SettingEntity();
        setting.setDailyReminderTime(DRtime.toString());
        setting.setGender(Gender.toString());
        setting.setInterestedAgeMax(MaxAge.toString());
        setting.setInterestedAgeMin(MinAge.toString());
        setting.setMaxDistance(MDsearch.toString());
        setting.setPrivateAccount(Private.isChecked());

        settingViewModel.updateSetting(this.getContext(), setting);
        Toast.makeText(getActivity(), "Settings Update", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(Constants.KEY_EMAIL, getArguments().getString(EMAILTEXT));
    }
}
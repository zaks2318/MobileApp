package com.example.ad340app;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SecondActTest {
    @Rule
    public ActivityScenarioRule<Activity2> activityScenarioRule
            = new ActivityScenarioRule<>(Activity2.class);
}

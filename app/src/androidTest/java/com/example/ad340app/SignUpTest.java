package com.example.ad340app;

import android.widget.DatePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class SignUpTest {
    @Rule
    public ActivityScenarioRule<SignUpActivity> activityScenarioRule
            = new ActivityScenarioRule<>(SignUpActivity.class);

    @Test
    public void canEnterNameAndSignUp() throws InterruptedException {
        onView(withId(R.id.nameInput)).perform(typeText("aaaaaaa"));
        onView(withId(R.id.emailInput)).perform(typeText("abcadw@gmail.com"));
        onView(withId(R.id.userNameInput)).perform(typeText("bbbbbbbb"));
        onView(withId(R.id.dialog)).perform((click()));
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(1900, 01, 01));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.submitBut)).perform((click()));
    }

}

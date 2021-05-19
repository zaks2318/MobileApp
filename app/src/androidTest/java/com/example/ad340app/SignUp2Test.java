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
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class SignUp2Test {
    @Rule
    public ActivityScenarioRule<signUp2Activity> activityScenarioRule
            = new ActivityScenarioRule<>(signUp2Activity.class);

    @Test
    public void canEnterNameAndSignUp() throws InterruptedException {
        onView(withId(R.id.nameInput)).perform(typeText("aaaaaaa"));
        onView(withId(R.id.emailInput)).perform(typeText("abcadw@gmail.com"));
        onView(withId(R.id.userNameInput)).perform(typeText("bbbbbbbb"));
        onView(withId(R.id.jobInput)).perform(typeText("ccccc"));
        onView(withId(R.id.descripInput)).perform(typeText("ddddddddddd"));

        onView(withId(R.id.dialog)).perform((click()));
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(1991, 01, 01));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.submitBut)).perform((click()));

    }

    @Test
        public void retainStateAfterRotate(){
        onView(withId(R.id.nameInput)).perform(typeText("aaaaaaa"));
        onView(withId(R.id.emailInput)).perform(typeText("abcadw@gmail.com"));
        onView(withId(R.id.userNameInput)).perform(typeText("bbbbbbbb"));
        onView(withId(R.id.jobInput)).perform(typeText("ccccc"));
        onView(withId(R.id.descripInput)).perform(typeText("ddddddddddd"));

        TestUtils.rotateScreen(TestUtils.getActivity(activityScenarioRule));

        onView(withId(R.id.nameInput)).check(matches(withText("aaaaaaa")));
        onView(withId(R.id.userNameInput)).check(matches(withText("bbbbbbbb")));
        onView(withId(R.id.emailInput)).check(matches(withText("abcadw@gmail.com")));
        onView(withId(R.id.jobInput)).check(matches(withText("ccccc")));
        onView(withId(R.id.descripInput)).check(matches(withText("ddddddddddd")));
    }

    @Test
    public void checkEmail() {
        onView(withId(R.id.emailInput)).perform(typeText("adwaqdmail.com"));
        onView(withId(R.id.dialog)).perform((click()));
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(1900, 01, 01));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.submitBut)).perform((click()));

        onView((allOf(withId(R.id.emailInput), hasErrorText("Email invalid"))));

    }

    @Test
    public void checkEmptyEmail() {
        onView(withId(R.id.emailInput)).perform(typeText(""));
        onView(withId(R.id.dialog)).perform((click()));
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(1900, 01, 01));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.submitBut)).perform((click()));

        onView((allOf(withId(R.id.emailInput), hasErrorText("Email can't be empty"))));

    }

    @Test
    public void checkEmptyUsername() {
        onView(withId(R.id.userNameInput)).perform(typeText(""));
        onView(withId(R.id.dialog)).perform((click()));
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(1900, 01, 01));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.submitBut)).perform((click()));

        onView((allOf(withId(R.id.userNameInput), hasErrorText("enter your user name pls"))));

    }

    @Test
    public void checkUsernameTooLong() {
        onView(withId(R.id.userNameInput)).perform(typeText("abcdefghijklmnopqrst"));
        onView(withId(R.id.dialog)).perform((click()));
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(1900, 01, 01));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.submitBut)).perform((click()));

        onView((allOf(withId(R.id.userNameInput), hasErrorText("username too long"))));

    }

    @Test
    public void checkEmptyName() {
        onView(withId(R.id.nameInput)).perform(typeText(""));
        onView(withId(R.id.dialog)).perform((click()));
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(1900, 01, 01));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.submitBut)).perform((click()));

        onView((allOf(withId(R.id.userNameInput), hasErrorText("enter your name pls"))));

    }

    @Test
    public void checkEmptyjob() {
        onView(withId(R.id.jobInput)).perform(typeText(""));
        onView(withId(R.id.dialog)).perform((click()));
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(1900, 01, 01));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.submitBut)).perform((click()));

        onView((allOf(withId(R.id.jobInput), hasErrorText("enter your job pls"))));

    }

    @Test
    public void checkEmptyDescription() {
        onView(withId(R.id.descripInput)).perform(typeText(""));
        onView(withId(R.id.dialog)).perform((click()));
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(1900, 01, 01));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.submitBut)).perform((click()));

        onView((allOf(withId(R.id.descripInput), hasErrorText("enter something about yourself pls"))));

    }

    @Test
    public void checkAge() {
        onView(withId(R.id.dialog)).perform((click()));
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020, 05, 01));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.submitBut)).perform((click()));

        onView((allOf(withId(R.id.editText), hasErrorText("you are under 18, so you can't sign up"))));

    }

}


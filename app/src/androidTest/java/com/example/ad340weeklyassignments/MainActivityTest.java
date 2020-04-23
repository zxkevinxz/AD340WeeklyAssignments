package com.example.ad340weeklyassignments;


import android.app.Activity;
import android.content.Context;
import android.os.RemoteException;
import android.widget.DatePicker;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.contrib.PickerActions.setDate;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private Context context = getInstrumentation().getTargetContext();

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void askForName() {
        onView(withId(R.id.my_name_date))
                .check(matches(withText(R.string.name_date)));
    }

    @Test
    public void noUsername() {
        onView(withId(R.id.name)).perform(typeText(context.getString(R.string.uTestName)));
        onView(withId(R.id.email)).perform(typeText(context.getString(R.string.uTestEmail)));
        onView(withId(R.id.username)).perform(clearText());
        onView(withId(R.id.dob_button)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(1983, 2, 16));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.submit)).perform(click());
        onView(withId(R.id.errorsMsg))
                .check(matches(withText(context.getString(R.string.ERR_USERNAME))));

    }
    @Test
    public void noName() {
        onView(withId(R.id.name)).perform(clearText());
        onView(withId(R.id.email)).perform(typeText(context.getString(R.string.uTestEmail)));
        onView(withId(R.id.username)).perform(typeText(context.getString(R.string.uTestUsername)));
        onView(withId(R.id.dob_button)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(1983, 2, 16));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.submit)).perform(click());
        onView(withId(R.id.errorsMsg))
                .check(matches(withText(context.getString(R.string.ERR_NAME))));

    }

    @Test
    public void badEmail() {
        onView(withId(R.id.name)).perform(typeText(context.getString(R.string.uTestName)));
        onView(withId(R.id.email)).perform(typeText(context.getString(R.string.uTestBadEmail)));
        onView(withId(R.id.username)).perform(typeText(context.getString(R.string.uTestUsername)));
        onView(withId(R.id.dob_button)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(1983, 2, 16));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.submit)).perform(click());
        onView(withId(R.id.errorsMsg))
                .check(matches(withText(context.getString(R.string.ERR_EMAIL))));

    }

    @Test
    public void noDate() {
        onView(withId(R.id.name)).perform(typeText(context.getString(R.string.uTestName)));
        onView(withId(R.id.email)).perform(typeText(context.getString(R.string.uTestEmail)));
        onView(withId(R.id.username)).perform(typeText(context.getString(R.string.uTestUsername)));
        onView(withId(R.id.submit)).perform(click());
        onView(withId(R.id.errorsMsg))
                .check(matches(withText(context.getString(R.string.ERR_NO_DOB))));

    }

    @Test
    public void tooYoung() {
        onView(withId(R.id.name)).perform(typeText(context.getString(R.string.uTestName)));
        onView(withId(R.id.email)).perform(typeText(context.getString(R.string.uTestEmail)));
        onView(withId(R.id.username)).perform(typeText(context.getString(R.string.uTestUsername)));
        onView(withId(R.id.dob_button)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(2010, 2, 16));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.submit)).perform(click());
        onView(withId(R.id.errorsMsg))
                .check(matches(withText(context.getString(R.string.ERR_DOB))));

    }

    @Test
    public void rotateDate() throws RemoteException {
        onView(withId(R.id.dob_button)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(1983, 2, 16));
        onView(withId(android.R.id.button1)).perform(click());
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        device.setOrientationRight();
        onView(withId(R.id.dob)).check(matches(withText("2/16/1983")));
        device.setOrientationNatural();
        onView(withId(R.id.dob)).check(matches(withText("2/16/1983")));
    }

    @Test
    public void testSuccessfulSignUp() {
        onView(withId(R.id.submit)).perform(click());
        onView(withId(R.id.name)).perform(typeText(context.getString(R.string.uTestName)));
        onView(withId(R.id.email)).perform(typeText(context.getString(R.string.uTestEmail)));
        onView(withId(R.id.username)).perform(typeText(context.getString(R.string.uTestUsername)));
        onView(withId(R.id.dob_button)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(1983, 2, 16));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.submit)).perform(click());
        onView(withId(R.id.results))
                .check(matches(withText("Thanks for signing up, zxkevinxz!")));
        onView(withId(R.id.newAccount)).perform(click());
        onView(withId(R.id.errorsMsg)).check(matches(withText("")));
    }
}
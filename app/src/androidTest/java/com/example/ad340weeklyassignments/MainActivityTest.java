package com.example.ad340weeklyassignments;


import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.constraintlayout.widget.Constraints.TAG;
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
        onView(withId(R.id.firstName)).perform(typeText(context.getString(R.string.uTestFirstName)));
        onView(withId(R.id.lastName)).perform(typeText(context.getString(R.string.uTestLastName)));
        onView(withId(R.id.email)).perform(typeText(context.getString(R.string.uTestEmail)));
        onView(withId(R.id.username)).perform(clearText());
        onView(withId(R.id.occupation)).perform(typeText(context.getString(R.string.uTestOccupation)));
        onView(withId(R.id.description)).perform(typeText(context.getString(R.string.uTestDescription)));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.dob_button)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(1983, 2, 16));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.submit)).perform(click());
        onView(withId(R.id.errorsMsg))
                .check(matches(withText(context.getString(R.string.ERR_USERNAME))));

    }
    @Test
    public void noFirstName() {
        onView(withId(R.id.firstName)).perform(clearText());
        onView(withId(R.id.lastName)).perform(typeText(context.getString(R.string.uTestLastName)));
        onView(withId(R.id.email)).perform(typeText(context.getString(R.string.uTestEmail)));
        onView(withId(R.id.username)).perform(typeText(context.getString(R.string.uTestUsername)));
        onView(withId(R.id.occupation)).perform(typeText(context.getString(R.string.uTestOccupation)));
        onView(withId(R.id.description)).perform(typeText(context.getString(R.string.uTestDescription)));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.dob_button)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(1983, 2, 16));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.submit)).perform(click());
        onView(withId(R.id.errorsMsg))
                .check(matches(withText(context.getString(R.string.ERR_FIRST_NAME))));
    }

    @Test
    public void firstNameTooLong() {
        onView(withId(R.id.firstName)).perform(typeText(context.getString(R.string.uTestNameLength)));
        onView(withId(R.id.lastName)).perform(typeText(context.getString(R.string.uTestLastName)));
        onView(withId(R.id.email)).perform(typeText(context.getString(R.string.uTestEmail)));
        onView(withId(R.id.username)).perform(typeText(context.getString(R.string.uTestUsername)));
        onView(withId(R.id.occupation)).perform(typeText(context.getString(R.string.uTestOccupation)));
        onView(withId(R.id.description)).perform(typeText(context.getString(R.string.uTestDescription)));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.dob_button)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(1983, 2, 16));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.submit)).perform(click());
        onView(withId(R.id.errorsMsg))
                .check(matches(withText(context.getString(R.string.ERR_NAME_LENGTH))));
    }

    @Test
    public void noLastName() {
        onView(withId(R.id.firstName)).perform(typeText(context.getString(R.string.uTestFirstName)));
        onView(withId(R.id.lastName)).perform(clearText());
        onView(withId(R.id.email)).perform(typeText(context.getString(R.string.uTestEmail)));
        onView(withId(R.id.username)).perform(typeText(context.getString(R.string.uTestUsername)));
        onView(withId(R.id.occupation)).perform(typeText(context.getString(R.string.uTestOccupation)));
        onView(withId(R.id.description)).perform(typeText(context.getString(R.string.uTestDescription)));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.dob_button)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(1983, 2, 16));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.submit)).perform(click());
        onView(withId(R.id.errorsMsg))
                .check(matches(withText(context.getString(R.string.ERR_LAST_NAME))));
    }

    @Test
    public void lastNameTooLong() {
        onView(withId(R.id.firstName)).perform(typeText(context.getString(R.string.uTestFirstName)));
        onView(withId(R.id.lastName)).perform(typeText(context.getString(R.string.uTestNameLength)));
        onView(withId(R.id.email)).perform(typeText(context.getString(R.string.uTestEmail)));
        onView(withId(R.id.username)).perform(typeText(context.getString(R.string.uTestUsername)));
        onView(withId(R.id.occupation)).perform(typeText(context.getString(R.string.uTestOccupation)));
        onView(withId(R.id.description)).perform(typeText(context.getString(R.string.uTestDescription)));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.dob_button)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(1983, 2, 16));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.submit)).perform(click());
        onView(withId(R.id.errorsMsg))
                .check(matches(withText(context.getString(R.string.ERR_NAME_LENGTH))));
    }

    @Test
    public void badEmail() {
        onView(withId(R.id.firstName)).perform(typeText(context.getString(R.string.uTestFirstName)));
        onView(withId(R.id.lastName)).perform(typeText(context.getString(R.string.uTestLastName)));
        onView(withId(R.id.email)).perform(typeText(context.getString(R.string.uTestBadEmail)));
        onView(withId(R.id.username)).perform(typeText(context.getString(R.string.uTestUsername)));
        onView(withId(R.id.occupation)).perform(typeText(context.getString(R.string.uTestOccupation)));
        onView(withId(R.id.description)).perform(typeText(context.getString(R.string.uTestDescription)));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.dob_button)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(1983, 2, 16));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.submit)).perform(click());
        onView(withId(R.id.errorsMsg))
                .check(matches(withText(context.getString(R.string.ERR_EMAIL))));

    }

    @Test
    public void noEmail() {
        onView(withId(R.id.firstName)).perform(typeText(context.getString(R.string.uTestFirstName)));
        onView(withId(R.id.lastName)).perform(typeText(context.getString(R.string.uTestLastName)));
        onView(withId(R.id.email)).perform(clearText());
        onView(withId(R.id.username)).perform(typeText(context.getString(R.string.uTestUsername)));
        onView(withId(R.id.occupation)).perform(typeText(context.getString(R.string.uTestOccupation)));
        onView(withId(R.id.description)).perform(typeText(context.getString(R.string.uTestDescription)));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.dob_button)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(1983, 2, 16));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.submit)).perform(click());
        onView(withId(R.id.errorsMsg))
                .check(matches(withText(context.getString(R.string.ERR_EMAIL))));

    }

    @Test
    public void noDate() {
        onView(withId(R.id.firstName)).perform(typeText(context.getString(R.string.uTestFirstName)));
        onView(withId(R.id.lastName)).perform(typeText(context.getString(R.string.uTestLastName)));
        onView(withId(R.id.email)).perform(typeText(context.getString(R.string.uTestEmail)));
        onView(withId(R.id.username)).perform(typeText(context.getString(R.string.uTestUsername)));
        onView(withId(R.id.occupation)).perform(typeText(context.getString(R.string.uTestOccupation)));
        onView(withId(R.id.description)).perform(typeText(context.getString(R.string.uTestDescription)));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.submit)).perform(click());
        onView(withId(R.id.errorsMsg))
                .check(matches(withText(context.getString(R.string.ERR_NO_DOB))));

    }

    @Test
    public void tooYoung() {
        onView(withId(R.id.firstName)).perform(typeText(context.getString(R.string.uTestFirstName)));
        onView(withId(R.id.lastName)).perform(typeText(context.getString(R.string.uTestLastName)));
        onView(withId(R.id.email)).perform(typeText(context.getString(R.string.uTestEmail)));
        onView(withId(R.id.username)).perform(typeText(context.getString(R.string.uTestUsername)));
        onView(withId(R.id.occupation)).perform(typeText(context.getString(R.string.uTestOccupation)));
        onView(withId(R.id.description)).perform(typeText(context.getString(R.string.uTestDescription)));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.dob_button)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(2010, 2, 16));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.submit)).perform(click());
        onView(withId(R.id.errorsMsg))
                .check(matches(withText(context.getString(R.string.ERR_DOB))));

    }

    @Test
    public void noOccupation() {
        onView(withId(R.id.firstName)).perform(typeText(context.getString(R.string.uTestFirstName)));
        onView(withId(R.id.lastName)).perform(typeText(context.getString(R.string.uTestLastName)));
        onView(withId(R.id.email)).perform(typeText(context.getString(R.string.uTestEmail)));
        onView(withId(R.id.username)).perform(typeText(context.getString(R.string.uTestUsername)));
        onView(withId(R.id.description)).perform(typeText(context.getString(R.string.uTestDescription)));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.dob_button)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(1983, 2, 16));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.submit)).perform(click());
        onView(withId(R.id.errorsMsg))
                .check(matches(withText(context.getString(R.string.ERR_OCCUPATION))));
    }

    @Test
    public void noDescription() {
        onView(withId(R.id.firstName)).perform(typeText(context.getString(R.string.uTestFirstName)));
        onView(withId(R.id.lastName)).perform(typeText(context.getString(R.string.uTestLastName)));
        onView(withId(R.id.email)).perform(typeText(context.getString(R.string.uTestEmail)));
        onView(withId(R.id.username)).perform(typeText(context.getString(R.string.uTestUsername)));
        onView(withId(R.id.occupation)).perform(typeText(context.getString(R.string.uTestOccupation)));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.dob_button)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(1983, 2, 16));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.submit)).perform(click());
        onView(withId(R.id.errorsMsg))
                .check(matches(withText(context.getString(R.string.ERR_DESCRIPTION))));
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

        onView(withId(R.id.firstName)).perform(typeText(context.getString(R.string.uTestFirstName)));
        onView(withId(R.id.lastName)).perform(typeText(context.getString(R.string.uTestLastName)));
        onView(withId(R.id.email)).perform(typeText(context.getString(R.string.uTestEmail)));
        onView(withId(R.id.username)).perform(typeText(context.getString(R.string.uTestUsername)));
        onView(withId(R.id.occupation)).perform(typeText(context.getString(R.string.uTestOccupation)));
        onView(withId(R.id.description)).perform(typeText(context.getString(R.string.uTestDescription)));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.dob_button)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(1983, 2, 16));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.submit)).perform(click());
        onView(withId(R.id.profileDescription))
                .check(matches(withText(context.getString(R.string.uTestDescription))));
        onView(withId(R.id.profileOccupation))
                .check(matches(withText(context.getString(R.string.uTestOccupation))));
        onView(withId(R.id.profileFirstName))
                .check(matches(withText(context.getString(R.string.uTestFirstName))));
        onView(withId(R.id.profileLastName))
                .check(matches(withText(context.getString(R.string.uTestLastName))));
        onView(withId(R.id.age))
                .check(matches(withText(context.getString(R.string.uTestAge))));
        Espresso.pressBack();
        onView(withId(R.id.errorsMsg))
                .check(matches(withText("")));



    }

    @Test
    public void testScrollView() {
        onView(withId(R.id.firstName)).perform(typeText(context.getString(R.string.uTestFirstName)));
        onView(withId(R.id.lastName)).perform(typeText(context.getString(R.string.uTestLastName)));
        onView(withId(R.id.email)).perform(typeText(context.getString(R.string.uTestEmail)));
        onView(withId(R.id.username)).perform(typeText(context.getString(R.string.uTestUsername)));
        onView(withId(R.id.occupation)).perform(typeText(context.getString(R.string.uTestOccupation)));
        onView(withId(R.id.description)).perform(typeText(context.getString(R.string.testScrollLorem)));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.dob_button)).perform(ViewActions.scrollTo(), click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(1983, 2, 16));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.submit)).perform(ViewActions.scrollTo(), click());
        onView(withId(R.id.profileFirstName))
                .check(matches(withText(context.getString(R.string.uTestFirstName))));
    }

}
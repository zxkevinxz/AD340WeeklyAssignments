package com.example.ad340weeklyassignments;


import android.content.Context;
import android.os.RemoteException;
import android.view.View;
import android.widget.DatePicker;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.UiDevice;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.PickerActions.setDate;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private Context context = getInstrumentation().getTargetContext();

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    private View decorView;

    @Before
    public void setUp() {
        activityScenarioRule.getScenario().onActivity(activity -> decorView = activity.getWindow().getDecorView());
    }

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    @Test
    public void askForName() {
        onView(withId(R.id.my_name_date))
                .check(matches(withText(R.string.name_date)));
    }

    @Test
    public void noUsername() throws InterruptedException {
        Thread.sleep(1500);
        Espresso.closeSoftKeyboard();
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
        onView(withId(R.id.username))
                .check(matches(hasErrorText(context.getString(R.string.ERR_USERNAME))));

    }
    @Test
    public void noFirstName() throws InterruptedException {
        Espresso.closeSoftKeyboard();
        Thread.sleep(1500);
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
        onView(withId(R.id.firstName))
                .check(matches(hasErrorText(context.getString(R.string.ERR_FIRST_NAME))));
    }

    @Test
    public void firstNameTooLong() throws InterruptedException {
        Espresso.closeSoftKeyboard();
        Thread.sleep(1500);
        onView(withId(R.id.firstName)).perform(clearText());
        onView(withId(R.id.firstName)).perform(typeText(context.getString(R.string.uTestNameLength)));
        onView(withId(R.id.lastName)).perform(clearText());
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
        onView(withId(R.id.firstName))
                .check(matches(hasErrorText(context.getString(R.string.ERR_NAME_LENGTH))));
    }

    @Test
    public void noLastName() throws InterruptedException {
        Espresso.closeSoftKeyboard();
        Thread.sleep(1500);
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
        onView(withId(R.id.lastName))
                .check(matches(hasErrorText(context.getString(R.string.ERR_LAST_NAME))));
    }

    @Test
    public void lastNameTooLong() throws InterruptedException {
        Espresso.closeSoftKeyboard();
        Thread.sleep(1500);
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
        onView(withId(R.id.lastName))
                .check(matches(hasErrorText(context.getString(R.string.ERR_NAME_LENGTH))));
    }

    @Test
    public void badEmail() throws InterruptedException {
        Espresso.closeSoftKeyboard();
        Thread.sleep(1500);
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
        onView(withId(R.id.email))
                .check(matches(hasErrorText(context.getString(R.string.ERR_EMAIL))));

    }

    @Test
    public void noEmail() throws InterruptedException {
        Espresso.closeSoftKeyboard();
        Thread.sleep(1500);
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
        onView(withId(R.id.email))
                .check(matches(hasErrorText(context.getString(R.string.ERR_EMAIL))));

    }

    @Test
    public void noDate() throws InterruptedException {
        Espresso.closeSoftKeyboard();
        Thread.sleep(1500);
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
    public void underAge() throws InterruptedException {
        Espresso.closeSoftKeyboard();
        Thread.sleep(1500);
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
    public void noOccupation() throws InterruptedException {
        Espresso.closeSoftKeyboard();
        Thread.sleep(1500);
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
        onView(withId(R.id.occupation))
                .check(matches(hasErrorText(context.getString(R.string.ERR_OCCUPATION))));
    }

    @Test
    public void badLengthOccupation() throws InterruptedException {
        Espresso.closeSoftKeyboard();
        Thread.sleep(1500);
        onView(withId(R.id.firstName)).perform(typeText(context.getString(R.string.uTestFirstName)));
        onView(withId(R.id.lastName)).perform(typeText(context.getString(R.string.uTestLastName)));
        onView(withId(R.id.email)).perform(typeText(context.getString(R.string.uTestEmail)));
        onView(withId(R.id.username)).perform(typeText(context.getString(R.string.uTestUsername)));
        onView(withId(R.id.occupation)).perform(typeText(context.getString(R.string.uTestNameLength)));
        onView(withId(R.id.description)).perform(typeText(context.getString(R.string.uTestDescription)));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.dob_button)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(1983, 2, 16));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.submit)).perform(click());
        onView(withId(R.id.occupation))
                .check(matches(hasErrorText(context.getString(R.string.ERR_OCC_LENGTH))));
    }

    @Test
    public void noDescription() throws InterruptedException {
        Espresso.closeSoftKeyboard();
        Thread.sleep(1500);
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
        onView(withId(R.id.description))
                .check(matches(hasErrorText(context.getString(R.string.ERR_DESCRIPTION))));
    }



    @Test
    public void rotateDate() throws RemoteException, InterruptedException {
        Espresso.closeSoftKeyboard();
        Thread.sleep(1500);
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
    public void rotateNoDate() throws RemoteException, InterruptedException {
        Espresso.closeSoftKeyboard();
        Thread.sleep(1500);
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        device.setOrientationRight();
        onView(withId(R.id.dob)).check(matches(withText(context.getString(R.string.empty_dob))));
        device.setOrientationNatural();
        onView(withId(R.id.dob)).check(matches(withText(context.getString(R.string.empty_dob))));
    }

    @Test
    public void testSuccessfulSignUp() throws InterruptedException {
        Espresso.closeSoftKeyboard();
        Thread.sleep(1500);
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
        Thread.sleep(1500);
        onView(withId(R.id.profileDescription))
                .check(matches(withText(context.getString(R.string.uTestDescription))));
        onView(withId(R.id.profileOccupation))
                .check(matches(withText(context.getString(R.string.uTestOccupation))));
        onView(withId(R.id.profileName))
                .check(matches(withText(context.getString(R.string.uTestFullName))));
        onView(withId(R.id.age))
                .check(matches(withText(context.getString(R.string.uTestAge))));
        Thread.sleep(1000);
        onView(allOf(withText("MATCHES"), isDescendantOfA(withId(R.id.tablayout))))
                .perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.scrollToPosition(5));
        Thread.sleep(1000);
        onView(withText(context.getString(R.string.uTestMike)))
                .check(matches(isDisplayed()));
        onView(withText(context.getString(R.string.uTestMatchJob)))
                .check(matches(isDisplayed()));
        onView(withText(context.getString(R.string.uTestMatchAge)))
                .check(matches(isDisplayed()));
        onView(withContentDescription(context.getString(R.string.uTestFavIconDesc)))
                .perform(click());
        onView(withText(R.string.uTestToast)).inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
        Thread.sleep(1500);
        onView(withContentDescription(context.getString(R.string.uTestFavIconDesc)))
                .perform(click());
        onView(allOf(withText("SETTINGS"), isDescendantOfA(withId(R.id.tablayout))))
                .perform(click());
        onView(withId(R.id.sGenderTitle))
                .check(matches(withText(R.string.gender_title)));
        onView(withId(R.id.sGenderInput)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(context.getString(R.string.genderTestMale))))
                .perform(click());
        onView(withId(R.id.sPrivacyTitle))
                .check(matches(withText(R.string.privacy_title)));
        onView(withId(R.id.sPrivacyInput)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(context.getString(R.string.privacyTestPublic))))
                .perform(click());
        onView(withId(R.id.sAgeRangeInput))
                .perform(typeText(context.getString(R.string.ageRangeTest30)));
        onView(withId(R.id.sDistanceInput))
                .perform(typeText(context.getString(R.string.distanceInputTest50)));
        onView(withId(R.id.sReminderTimeInput)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(context.getString(R.string.reminderTimeTest12pm))))
                .perform(click());
        onView(withId(R.id.save_settings)).perform(click());
        onView(withText(R.string.settings_saved_toast)).inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
        onView(withId(R.id.sGenderInput))
                .check(matches(withSpinnerText(containsString(context.getString(R.string.genderTestMale)))));
        onView(withId(R.id.sPrivacyInput))
                .check(matches(withSpinnerText(containsString(context.getString(R.string.privacyTestPublic)))));
        onView(withId(R.id.sReminderTimeInput))
                .check(matches(withSpinnerText(containsString(context.getString(R.string.reminderTimeTest12pm)))));
        onView(withId(R.id.sDistanceInput))
                .check(matches(withText(context.getString(R.string.distanceInputTest50))));
        onView(withId(R.id.sAgeRangeInput))
                .check(matches(withText(context.getString(R.string.ageRangeTest30))));
        onView(withId(R.id.settings)).perform(swipeRight());
        Espresso.pressBack();
    }

    @Test
    public void testScrollView() throws InterruptedException {
        Espresso.closeSoftKeyboard();
        Thread.sleep(1500);
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
        onView(withId(R.id.profileName))
                .check(matches(withText(context.getString(R.string.uTestFullName))));
    }

}


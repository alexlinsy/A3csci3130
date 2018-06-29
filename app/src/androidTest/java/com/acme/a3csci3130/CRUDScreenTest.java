package com.acme.a3csci3130;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

public class CRUDScreenTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule(MainActivity.class);

    @Test
    public void createContactButtonClick() {
        onView(withId(R.id.submitButton)).perform(click());
        //Create contact
        onView(withId(R.id.name)).perform(typeText("Ian"));
        onView(withId(R.id.email)).perform(typeText("test@gmail.com"));
        onView(withId(R.id.businessNumberText)).perform(typeText("123456789"));
        onView(withId(R.id.addressText)).perform(typeText("1024 Orange Street"), closeSoftKeyboard());
        onView(withId(R.id.primaryBusinessSpinner)).perform(click());
        onData(anything()).atPosition(1).perform(click());
        onView(withId(R.id.provinceSpinner)).perform(click());
        onData(anything()).atPosition(3).perform(click());
        onView(withId(R.id.submitButton)).perform(click());

        //Check if the data get into firebase and show on the UI
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());

        //Check if the data matches the data that inputted by the user
        onView(withId(R.id.name)).check(matches(withText("Ian")));
        onView(withId(R.id.email)).check(matches(withText("test@gmail.com")));
        onView(withId(R.id.businessNumberText)).check(matches(withText("123456789")));
        onView(withId(R.id.addressText)).check(matches(withText("1024 Orange Street")));
        onView(withId(R.id.currentPrimaryBusiness)).check(matches(withText("Distributor")));
        onView(withId(R.id.currentProvince)).check(matches(withText("NB")));

        //Update data
        onView(withId(R.id.name)).perform(clearText(), typeText("Ianny"));
        onView(withId(R.id.email)).perform(clearText(), typeText("test007@gmail.com"));
        onView(withId(R.id.businessNumberText)).perform(clearText(), typeText("987654321"));
        onView(withId(R.id.addressText)).perform(clearText(), typeText("500 Apple Street"), closeSoftKeyboard());
        onView(withId(R.id.primaryBusinessSpinner)).perform(click());
        onData(anything()).atPosition(2).perform(click());
        onView(withId(R.id.provinceSpinner)).perform(click());
        onData(anything()).atPosition(1).perform(click());
        onView(withId(R.id.updateButton)).perform(click());

        //Check if the update data matches the data that inputted by the user
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.name)).check(matches(withText("Ianny")));
        onView(withId(R.id.email)).check(matches(withText("test007@gmail.com")));
        onView(withId(R.id.businessNumberText)).check(matches(withText("987654321")));
        onView(withId(R.id.addressText)).check(matches(withText("500 Apple Street")));
        onView(withId(R.id.currentPrimaryBusiness)).check(matches(withText("Processor")));
        onView(withId(R.id.currentProvince)).check(matches(withText("BC")));

        //Check if the data can be erased
        onView(withId(R.id.deleteButton)).perform(click());

    }
}

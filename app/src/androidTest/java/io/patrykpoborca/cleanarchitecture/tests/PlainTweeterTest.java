package io.patrykpoborca.cleanarchitecture.tests;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import junit.framework.Assert;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.patrykpoborca.cleanarchitecture.R;
import io.patrykpoborca.cleanarchitecture.TestHelper;
import io.patrykpoborca.cleanarchitecture.dagger.components.DaggerActivityInjectorComponent;
import io.patrykpoborca.cleanarchitecture.mockimpl.MockLocalDataCache;
import io.patrykpoborca.cleanarchitecture.mockimpl.MockOkHTTP;
import io.patrykpoborca.cleanarchitecture.mockimpl.MockRetrofit;

import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class PlainTweeterTest {

    @Rule
    public ActivityTestRule<io.patrykpoborca.cleanarchitecture.ui.PlainTweeterActivity> plainTweeterActivity = new ActivityTestRule<>(io.patrykpoborca.cleanarchitecture.ui.PlainTweeterActivity.class,
            false,
            true);

    private static final String SOME_URL = "SOME_URL";
    private static final String USER_PASSWORD = "USER_PASSWORD";
    private static final String USER_NAME = "USER_NAME";
    private String fetchedTweet;

    @Before
    public void setUp() {
        DaggerActivityInjectorComponent
                .builder()
                .baseComponent(TestHelper.getBaseComponent())
                .build()
                .inject(plainTweeterActivity.getActivity());

        Assert.assertTrue(TestHelper.getBaseComponent().getLocalDataCache() instanceof MockLocalDataCache);
        Assert.assertTrue(TestHelper.getBaseComponent().getRetrofit() instanceof MockRetrofit);
        Assert.assertTrue(TestHelper.getBaseComponent().getOkHTTP() instanceof MockOkHTTP);

    }

    @Test
    public void testWebpage(){
        onView(withId(R.id.some_url))
                .perform(typeText(SOME_URL));

        onView(withId(R.id.some_url))
                .check(matches(new TypeSafeMatcher<View>() {
                    @Override
                    protected boolean matchesSafely(View item) {
                        TextView text = ((TextView) item);
                        return text.getText().toString().contains(SOME_URL);
                    }

                    @Override
                    public void describeTo(Description description) {

                    }
                }));


        onView(withId(R.id.request_website_button))
                .perform(click());

        onView(withId(R.id.webpage_text))
                .check(matches(new TypeSafeMatcher<View>(){
                    @Override
                    protected boolean matchesSafely(View item) {
                        TextView text = ((TextView)item);
                        return text.getText().toString().contains(SOME_URL);
                    }

                    @Override
                    public void describeTo(Description description) {

                    }
                }));
    }

    @Test
    public void testLogin(){
        onView(withId(R.id.user_name))
                .perform(typeText(USER_NAME));

        onView(withId(R.id.user_password))
                .perform(scrollTo())
                .perform(typeText(USER_PASSWORD));

        //login
        onView(withId(R.id.user_login_button))
                .perform(scrollTo())
                .perform(click());

        onView(withId(R.id.container))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));

        //logout
        onView(withId(R.id.user_login_button))
                .perform(click());

        onView(withId(R.id.container))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void testTweets(){
        onView(withId(R.id.current_tweet)).check(matches(withText(R.string.hello_world)));
        onView(withId(R.id.fetch_tweet_button))
                .perform(scrollTo())
                .perform(click());

        onView(withId(R.id.current_tweet)).check(matches(new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                boolean result = item instanceof TextView
                        && !((TextView) item).getText().toString().contains(plainTweeterActivity.getActivity().getResources().getString(R.string.hello_world));
                fetchedTweet = ((TextView) item).getText().toString();

                return result;
            }

            @Override
            public void describeTo(Description description) {

            }
        }));

        onView(withId(R.id.fetch_last_two_tweets)).perform(click());

        onView(withId(R.id.past_tweets_container)).check(matches(new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                ViewGroup parent = ((ViewGroup) item);

                for (int i = 0; i < parent.getChildCount(); i++) {
                    TextView textView = (TextView) parent.getChildAt(i);
                    if (fetchedTweet.contains(textView.getText().toString())) {
                        return true;
                    }
                }

                return false;
            }

            @Override
            public void describeTo(Description description) {

            }
        }));
    }

}

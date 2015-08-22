package tests;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.inject.Inject;

import helper.TestHelper;
import io.patrykpoborca.cleanarchitecture.ui.MVVM.MainViewModel;
import mockimpl.MockLocalDataCache;
import mockimpl.MockOkHTTP;
import mockimpl.MockRetrofit;

@RunWith(JUnit4.class)
public class MVVMTest {

    private static final String SOME_URL = "SOME_URL";
    private static final String USER_PASSWORD = "USER_PASSWORD";
    private static final String USER_NAME = "USER_NAME";
    private String tweetedTweet;

    @Inject
    MainViewModel viewModel;

    @Before
    public void setUp() {
        TestHelper.getTestClassInjector()
                .inject(this);

        Assert.assertTrue(TestHelper.getBaseComponent().getLocalDataCache() instanceof MockLocalDataCache);
        Assert.assertTrue(TestHelper.getBaseComponent().getRetrofit() instanceof MockRetrofit);
        Assert.assertTrue(TestHelper.getBaseComponent().getOkHTTP() instanceof MockOkHTTP);

    }


    @Test
    public void testWebPage() {
        viewModel.loadWebPage(SOME_URL)
                .toBlocking()
                .forEach(s -> {
                    Assert.assertTrue(s.contains(MockRetrofit.MOCKED_STRING)
                            && s.contains(SOME_URL));
                });
    }

    @Test
    public void testLogin() {
        Assert.assertFalse(viewModel.isLoggedIn());
        viewModel.toggleLogin(USER_NAME, USER_PASSWORD)
                .toBlocking()
                .forEach(
                        user -> {
                            Assert.assertNotNull(user);
                            Assert.assertEquals(user.getUserName(), USER_NAME);
                        }
                );
        Assert.assertTrue(viewModel.isLoggedIn());
    }

    @Test
    public void testTweets() {
        //Sanity test first to see if underlying logic of tweeter api has changed
        viewModel.fetchPreviousTweets()
                .toBlocking()
                .forEach(list -> {
                    Assert.assertNotNull(list);
                });

    }

    @Test
    public void testTweetList() {

        viewModel.fetchCurrentTweet()
                .toBlocking()
                .forEach(tweet -> {
                    tweetedTweet = tweet;
                });

        viewModel.fetchPreviousTweets()
                .toBlocking()
                .forEach(list -> {
                    boolean exists = false;

                    for (int i = 0; i < list.size(); i++) {
                        if (tweetedTweet.contains(list.get(i))) {
                            exists = true;
                            break;
                        }
                    }
                    Assert.assertTrue(exists);
                });
    }

}

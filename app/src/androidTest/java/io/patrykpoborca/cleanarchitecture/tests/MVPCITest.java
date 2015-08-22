package io.patrykpoborca.cleanarchitecture.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.inject.Inject;

import io.patrykpoborca.cleanarchitecture.TestHelper;
import io.patrykpoborca.cleanarchitecture.mockimpl.MockLocalDataCache;
import io.patrykpoborca.cleanarchitecture.mockimpl.MockMVPCIPview;
import io.patrykpoborca.cleanarchitecture.mockimpl.MockOkHTTP;
import io.patrykpoborca.cleanarchitecture.mockimpl.MockRetrofit;
import io.patrykpoborca.cleanarchitecture.ui.MVPCI.MainMVPCIPresenter;

@RunWith(JUnit4.class)
public class MVPCITest {

    private static final String SOME_URL = "SOME_URL";
    private static final String USER_PASSWORD = "USER_PASSWORD";
    private static final String USER_NAME = "USER_NAME";
    private String tweetedTweet;

    @Inject
    MainMVPCIPresenter presenter;

    @Inject
    MockMVPCIPview pView;
    @Before
    public void setUp(){
        TestHelper.getTestClassInjector()
                .inject(this);
        presenter.registerPresenter(pView);

        junit.framework.Assert.assertTrue(TestHelper.getBaseComponent().getLocalDataCache() instanceof MockLocalDataCache);
        junit.framework.Assert.assertTrue(TestHelper.getBaseComponent().getRetrofit() instanceof MockRetrofit);
        junit.framework.Assert.assertTrue(TestHelper.getBaseComponent().getOkHTTP() instanceof MockOkHTTP);
    }

    @Test
    public void testWebPage(){
        presenter.loadWebPage(SOME_URL)
                .toBlocking()
                .forEach(s -> {
                    junit.framework.Assert.assertTrue(s.contains(MockRetrofit.MOCKED_STRING)
                            && s.contains(SOME_URL));
                });
    }

    @Test
    public void testLogin(){
        Assert.assertTrue(pView.loggedInProfile == null);
        presenter.toggleLogin(USER_NAME, USER_PASSWORD);
        TestHelper.waitFor(() -> pView.loggedInProfile != null);

        Assert.assertTrue(pView.loggedInProfile.getUserName().equals(USER_NAME));
        presenter.toggleLogin(USER_NAME, USER_PASSWORD);
        TestHelper.waitFor(() -> pView.loggedOutCalled);
        Assert.assertTrue(pView.loggedOutCalled);
    }

    @Test
    public void testTweets() {
        //Sanity test first to see if underlying logic of tweeter api has changed
        presenter.fetchPreviousTweets()
                .toBlocking()
                .forEach(list -> {
                    Assert.assertNotNull(list);
                });

    }

    @Test
    public void testTweetList(){
        presenter.fetchCurrentTweet()
                .toBlocking()
                .forEach(tweet -> {
                    tweetedTweet = tweet;
                });

        presenter.fetchPreviousTweets()
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

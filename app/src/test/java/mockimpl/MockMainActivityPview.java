package mockimpl;

import java.util.List;

import io.patrykpoborca.cleanarchitecture.ui.MVP.interfaces.MainMVPPView;

public class MockMainActivityPview implements MainMVPPView {

    public String fetchedTweet = null;
    public List<String> previousTweets = null;
    public boolean setUserButtonTextCalled = false;
    public boolean toggleProgressBarCalled = false;
    public boolean toggleLoginContainerCalled = false;
    public String displayWebpage = null;
    public boolean displayToastCalled = false;

    @Override
    public void displayFetchedTweet(String tweet) {
        fetchedTweet = tweet;
    }

    @Override
    public void displayPreviousTweets(List<String> list) {
        previousTweets = list;
    }

    @Override
    public void setUserButtonText(String text) {
        setUserButtonTextCalled = true;
    }

    @Override
    public void toggleLoginContainer(boolean b) {
        toggleLoginContainerCalled = true;
    }

    @Override
    public void displayWebpage(String html) {
        displayWebpage = html;
    }

    @Override
    public void toggleProgressBar(boolean loading) {
        toggleProgressBarCalled = true;
    }

    @Override
    public void displayToast(String toast) {
        displayToastCalled = true;
    }
}

package io.patrykpoborca.cleanarchitecture.ui.MVP;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.patrykpoborca.cleanarchitecture.CleanArchitectureApplication;
import io.patrykpoborca.cleanarchitecture.R;
import io.patrykpoborca.cleanarchitecture.dagger.components.DaggerActivityInjectorComponent;
import io.patrykpoborca.cleanarchitecture.ui.MVP.base.BasePresenterActivity;
import io.patrykpoborca.cleanarchitecture.ui.MVP.interfaces.MainMVPPView;
import io.patrykpoborca.cleanarchitecture.ui.MVP.interfaces.MainMVPPresenter;
import io.patrykpoborca.cleanarchitecture.util.Utility;

/**
 * Created by Patryk on 7/27/2015.
 */
public class MainActivityMVP extends BasePresenterActivity<MainMVPPresenter> implements MainMVPPView {

    @Inject
    MainMVPPresenter presenter;

    @Bind(R.id.fetch_tweet_button)
    Button fetchTweetButton;

    @Bind(R.id.fetch_last_two_tweets)
    Button fetchLastTwoButton;

    @Bind(R.id.current_tweet)
    TextView currentTweetTextView;

    @Bind(R.id.past_tweets_container)
    LinearLayout pastTweetContainer;

    @Bind(R.id.user_login_button)
    Button loginButton;

    @Bind(R.id.user_name)
    TextView userNameTextView;

    @Bind(R.id.user_password)
    TextView userPasswordTextView;

    @Bind(R.id.container)
    ViewGroup container;

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == fetchLastTwoButton){
                getPresenter().fetchPreviousTweets();
            }
            else if(view == fetchTweetButton){
                getPresenter().fetchCurrentTweet();
            }
            if(view == loginButton){
                getPresenter().toggleLogin(userNameTextView.getText().toString(),
                                    userPasswordTextView.getText().toString());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        fetchLastTwoButton.setOnClickListener(onClickListener);
        fetchTweetButton.setOnClickListener(onClickListener);
        loginButton.setOnClickListener(onClickListener);
        setTitle("MVP Activity IMPL");
    }

    @Override
    protected MainMVPPresenter getPresenter() {
        if(presenter == null){

            DaggerActivityInjectorComponent.builder()
                        .twitterComponent(CleanArchitectureApplication.getTwitterAPIComponent())
                        .baseComponent(CleanArchitectureApplication.getBaseComponent())
                        .build()
                        .inject(this);
        }

        return presenter;
    }



    @Override
    public void displayFetchedTweet(String tweet) {
        currentTweetTextView.setText(tweet);
    }

    @Override
    public void displayPreviousTweets(List<String> tweets) {
        pastTweetContainer.removeAllViews(); //clear container...
        for(int i= 0;  i < tweets.size(); i++){
            TextView text = new TextView(this);
            text.setText(tweets.get(i));
            pastTweetContainer.addView(text);
        }
    }

    @Override
    protected void registerViewToPresenter() {
        getPresenter().registerView(this);
    }

    @Override
    public void displayToast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_LONG).show();
    }

    @Override
    public void toggleProgressBar(boolean loading) {
        Utility.toggleProgressbar(this, loading);
    }

    @Override
    public void setUserButtonText(String text) {
        this.loginButton.setText(text);
    }

    @Override
    public void toggleProgressbar(boolean show) {
        Utility.toggleProgressbar(this, show);
    }

    @Override
    public void toggleLoginContainer(boolean b) {
        container.setVisibility(b ? View.VISIBLE : View.GONE);
    }
}

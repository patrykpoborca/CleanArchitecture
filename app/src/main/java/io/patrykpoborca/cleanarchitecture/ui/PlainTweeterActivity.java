package io.patrykpoborca.cleanarchitecture.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import io.patrykpoborca.cleanarchitecture.network.TweeterApi;
import io.patrykpoborca.cleanarchitecture.network.base.Retrofit;
import io.patrykpoborca.cleanarchitecture.ui.MVPCI.models.UserProfile;
import io.patrykpoborca.cleanarchitecture.util.Utility;

public class PlainTweeterActivity extends BaseCleanArchitectureActivity {

    private static final int TWEET_COUNT = 2;
    @Inject
    TweeterApi tweeterApi;

    @Inject
    Retrofit retrofit;

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
    @Bind(R.id.some_url)
    EditText urlText;
    @Bind(R.id.webpage_text)
    TextView websiteText;
    @Bind(R.id.request_website_button)
    Button websiteFetchbutton;
    @Bind(R.id.help_history)
    View helpHistory;
    @Bind(R.id.help_login)
    View helpLogin;
    @Bind(R.id.help_url)
    View helpUrl;


    private int tweetsAdded = 0;

    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Utility.toggleProgressbar(PlainTweeterActivity.this, true);
            if (view == fetchLastTwoButton) {
                registerSubscription(
                        tweeterApi.fetchXrecents(2)
                                .subscribe(PlainTweeterActivity.this::displayPreviousTweets)
                );
            }
            else if (view == fetchTweetButton) {
                registerSubscription(
                        tweeterApi.getTweet()
                                .subscribe(s -> {
                                    currentTweetTextView.setText(s);
                                    tweetsAdded++;
                                    if (tweetsAdded > TWEET_COUNT) {
                                        Toast.makeText(PlainTweeterActivity.this, "Tweet size exceeded " + TWEET_COUNT, Toast.LENGTH_LONG).show();
                                    }
                                    Utility.toggleProgressbar(PlainTweeterActivity.this, false);
                                })
                );
            }
            else if (view == loginButton) {
                if (tweeterApi.isLoggedIn()) {
                    registerSubscription(tweeterApi.logout()
                            .subscribe(s -> {
                                container.setVisibility(View.VISIBLE);
                                loginButton.setText(R.string.log_user_in);
                                Utility.toggleProgressbar(PlainTweeterActivity.this, false);
                            }));
                }
                else {
                    registerSubscription(
                            tweeterApi.login(
                                    userNameTextView.getText().toString(),
                                    userPasswordTextView.getText().toString())
                                    .subscribe(PlainTweeterActivity.this::userLogin)
                    );
                }
            }
            else if (view == websiteFetchbutton) {
                retrofit.fetchSomePage(urlText.getText().toString())
                        .subscribe(s -> {
                            websiteText.setText(Html.fromHtml(s));
                            Utility.toggleProgressbar(PlainTweeterActivity.this, false);
                        });
            }

        }
    };

    private final View.OnClickListener dialogClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == helpHistory) {
                new AlertDialog.Builder(PlainTweeterActivity.this)
                        .setMessage(R.string.history_text)
                        .setPositiveButton("Ok", null)
                        .create()
                        .show();
            }
            else if (view == helpUrl) {
                new AlertDialog.Builder(PlainTweeterActivity.this)
                        .setMessage(R.string.url_text)
                        .setPositiveButton("Ok", null)
                        .create()
                        .show();
            }
            else if (view == helpLogin) {
                new AlertDialog.Builder(PlainTweeterActivity.this)
                        .setMessage(R.string.login_text)
                        .setPositiveButton("Ok", null)
                        .create()
                        .show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerActivityInjectorComponent.builder()
                .baseComponent(CleanArchitectureApplication.getBaseComponent())
                .build()
                .inject(this);

        ButterKnife.bind(this);

        setTitle("Plain Activity IMPL");

        this.fetchLastTwoButton.setOnClickListener(onClickListener);
        this.fetchTweetButton.setOnClickListener(onClickListener);
        this.loginButton.setOnClickListener(onClickListener);
        this.websiteFetchbutton.setOnClickListener(onClickListener);
        this.helpHistory.setOnClickListener(dialogClickListener);
        this.helpLogin.setOnClickListener(dialogClickListener);
        this.helpUrl.setOnClickListener(dialogClickListener);
    }

    public void displayPreviousTweets(List<String> tweets) {
        pastTweetContainer.removeAllViews(); //clear container...
        Utility.toggleProgressbar(PlainTweeterActivity.this, false);
        for (int i = 0; i < tweets.size(); i++) {
            TextView text = new TextView(this);
            text.setText(tweets.get(i));
            pastTweetContainer.addView(text);
        }
    }

    public void userLogin(UserProfile profile) {
        Utility.toggleProgressbar(this, false);

        container.setVisibility(View.GONE);
        loginButton.setText("Log " + profile.getUserName() + " out");
    }

    public static Intent newInstance(Context context) {
        return new Intent(context, PlainTweeterActivity.class);
    }
}

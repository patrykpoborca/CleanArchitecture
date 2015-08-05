package io.patrykpoborca.cleanarchitecture.ui;

import android.os.Bundle;
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
import io.patrykpoborca.cleanarchitecture.ui.MVPIC.models.UserProfile;
import io.patrykpoborca.cleanarchitecture.util.Utility;

public class MainActivityStupid extends BaseCAActivity {

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

    private int tweetsAdded = 0;

    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Utility.toggleProgressbar(MainActivityStupid.this, true);
            
            if(view == fetchLastTwoButton){
                registerSubscription(
                        tweeterApi.fetchXrecents(2)
                                .subscribe(MainActivityStupid.this::displayPreviousTweets)
                );
            }
            else if(view == fetchTweetButton){
                registerSubscription(
                        tweeterApi.getTweet()
                                .subscribe(s -> {
                                    currentTweetTextView.setText(s);
                                    tweetsAdded++;
                                    if (tweetsAdded > TWEET_COUNT) {
                                        Toast.makeText(MainActivityStupid.this, "Tweet size exceeded " + TWEET_COUNT, Toast.LENGTH_LONG).show();
                                    }
                                    Utility.toggleProgressbar(MainActivityStupid.this, false);
                                })
                );
            }
            else if(view == loginButton){
                if(tweeterApi.isLoggedIn()){
                    registerSubscription(tweeterApi.logout()
                            .subscribe(s -> {
                                container.setVisibility(View.VISIBLE);
                                loginButton.setText(R.string.log_user_in);
                                Utility.toggleProgressbar(MainActivityStupid.this, false);
                            }));
                }
                else {
                    registerSubscription(
                            tweeterApi.login(
                                    userNameTextView.getText().toString(),
                                    userPasswordTextView.getText().toString())
                                    .subscribe(MainActivityStupid.this::userLogin)
                    );
                }
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

        setTitle("Stupid Activity IMPL");

        fetchLastTwoButton.setOnClickListener(onClickListener);
        fetchTweetButton.setOnClickListener(onClickListener);
        loginButton.setOnClickListener(onClickListener);
    }

    public void displayPreviousTweets(List<String> tweets) {
        pastTweetContainer.removeAllViews(); //clear container...
        Utility.toggleProgressbar(MainActivityStupid.this, false);
        for(int i= 0;  i < tweets.size(); i++){
            TextView text = new TextView(this);
            text.setText(tweets.get(i));
            pastTweetContainer.addView(text);
        }
    }
    
    public void userLogin(UserProfile profile){
        Utility.toggleProgressbar(this, false);

        container.setVisibility(View.GONE);
        loginButton.setText("Log " + profile.getUserName() + " out");
    }
}

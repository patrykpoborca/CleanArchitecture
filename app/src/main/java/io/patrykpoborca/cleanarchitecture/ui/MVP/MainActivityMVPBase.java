package io.patrykpoborca.cleanarchitecture.ui.MVP;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.patrykpoborca.cleanarchitecture.CleanArchitectureApplication;
import io.patrykpoborca.cleanarchitecture.R;
import io.patrykpoborca.cleanarchitecture.dagger.components.DaggerActivityInjectorComponent;
import io.patrykpoborca.cleanarchitecture.ui.MVP.base.BasePresenterActivity;
import io.patrykpoborca.cleanarchitecture.ui.MVP.interfaces.MainActivityPView;
import io.patrykpoborca.cleanarchitecture.ui.MVP.interfaces.MainActivityPresenter;

/**
 * Created by Patryk on 7/27/2015.
 */
public class MainActivityMVPBase extends BasePresenterActivity<MainActivityPresenter> implements MainActivityPView{

    @Inject
    MainActivityPresenter presenter;

    @Bind(R.id.fetch_tweet_button)
    Button fetchTweetButton;

    @Bind(R.id.fetch_last_two_tweets)
    Button fetchLastTwoButton;

    @Bind(R.id.current_tweet)
    TextView currentTweetTextView;

    @Bind(R.id.past_tweets_container)
    LinearLayout pastTweetContainer;

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == fetchLastTwoButton){
                getPresenter().fetchTwoTweets();
            }
            else if(view == fetchTweetButton){
                getPresenter().fetchTweet();
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
        setTitle("MVP Activity IMPL");
    }

    @Override
    protected MainActivityPresenter getPresenter() {
        if(presenter == null){
            //dagger stuff!
            DaggerActivityInjectorComponent.builder().twitterComponent(CleanArchitectureApplication.getTwitterAPIComponent())
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
    public void displayPreviousTweets(ArrayList<String> tweets) {
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
}

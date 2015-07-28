package io.patrykpoborca.cleanarchitecture.ui.MVPIC;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import io.patrykpoborca.cleanarchitecture.R;
import io.patrykpoborca.cleanarchitecture.ui.MVP.base.BasePresenterActivity;

/**
 * Created by Patryk on 7/27/2015.
 */
public class MainActivityMVPIC extends BasePresenterActivity<> {

    @Bind(R.id.fetch_tweet_button)
    Button fetchTweetButton;

    @Bind(R.id.fetch_last_two_tweets)
    Button fetchLastTwoButton;

    @Bind(R.id.current_tweet)
    TextView currentTweetTextView;

    @Bind(R.id.past_tweets_container)
    LinearLayout pastTweetContainer;
}

package io.patrykpoborca.cleanarchitecture.ui.MVPIC;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.patrykpoborca.cleanarchitecture.CleanArchitectureApplication;
import io.patrykpoborca.cleanarchitecture.R;
import io.patrykpoborca.cleanarchitecture.dagger.components.ActivityInjectorComponent;
import io.patrykpoborca.cleanarchitecture.dagger.components.DaggerActivityInjectorComponent;
import io.patrykpoborca.cleanarchitecture.dagger.modules.TwitterModule;
import io.patrykpoborca.cleanarchitecture.ui.MVP.base.BasePresenterActivity;
import io.patrykpoborca.cleanarchitecture.ui.MVPIC.base.BasePresenterActivityMVPIC;
import io.patrykpoborca.cleanarchitecture.ui.MVPIC.interfaces.MainActivityMVPICPview;
import io.patrykpoborca.cleanarchitecture.ui.MVPIC.interfaces.MainMVPICPresenter;

/**
 * Created by Patryk on 7/27/2015.
 */

/**
 * Presenter as a Supervising controller is actually a bridge between observable models and the view, any complex operations are performed within the controller, basic databinding
 * circumvents a lot of unecessary boilerplate.
 *  Model <- Presenter -> View
 */
public class MainActivityMVPIC extends BasePresenterActivityMVPIC<MainMVPICPresenter> implements MainActivityMVPICPview {

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

    @Inject
    MainMVPICPresenter presenter;

    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == fetchTweetButton){
                registerSubscription(
                getPresenter().fetchCurrentTweet().subscribe(s -> currentTweetTextView.setText(s + " hihihi")));
            }
            else if(view == fetchLastTwoButton){
                pastTweetContainer.removeAllViews(); //clear container...
                registerSubscription(
                        getPresenter().fetchPreviousTweets().subscribe(s -> addTweetToList(s))
                );
            }
            else if(view == loginButton){
                registerSubscription(
                        getPresenter().logUserIn()
                                .subscribe(user -> Toast.makeText(MainActivityMVPIC.this, user.getFormattedCredentials(), Toast.LENGTH_SHORT).show())
                );
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mvpci);
        ButterKnife.bind(this);
        fetchLastTwoButton.setOnClickListener(onClickListener);
        fetchTweetButton.setOnClickListener(onClickListener);
        loginButton.setOnClickListener(onClickListener);
    }

    @Override
    protected void registerViewToPresenter() {
        getPresenter().registerView(this);
    }

    @Override
    protected MainMVPICPresenter getPresenter() {

        if(presenter == null){
            DaggerActivityInjectorComponent.builder()
                    .baseComponent(CleanArchitectureApplication.getBaseComponent())
                    .twitterComponent(CleanArchitectureApplication.getTwitterAPIComponent())
                    .build()
                    .inject(this);
        }

        return presenter;
    }

    @Override
    public void weHaveExceeded3TweetsOnBackend() {
        Toast.makeText(this, "We have exceeded 3 tweets on backend", Toast.LENGTH_LONG).show();
    }

    private void addTweetToList(String tweet){
        TextView textView = new TextView(this);
        textView.setText(tweet);
        pastTweetContainer.addView(textView);
    }
}

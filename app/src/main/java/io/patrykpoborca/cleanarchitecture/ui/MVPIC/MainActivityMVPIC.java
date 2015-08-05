package io.patrykpoborca.cleanarchitecture.ui.MVPIC;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import io.patrykpoborca.cleanarchitecture.ui.MVPIC.base.BasePresenterActivityMVPIC;
import io.patrykpoborca.cleanarchitecture.ui.MVPIC.interfaces.MainActivityMVPICPview;
import io.patrykpoborca.cleanarchitecture.ui.MVPIC.models.UserProfile;
import io.patrykpoborca.cleanarchitecture.util.Utility;
import rx.internal.util.UtilityFunctions;

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

    @Bind(R.id.user_name)
    TextView userNameTextView;

    @Bind(R.id.user_password)
    TextView userPasswordTextView;

    @Bind(R.id.container)
    ViewGroup container;


    @Inject
    MainMVPICPresenter presenter;

    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == fetchTweetButton){
                registerSubscription(
                        getPresenter().fetchCurrentTweet().subscribe(s -> currentTweetTextView.setText(s)));
            }
            else if(view == fetchLastTwoButton){
                pastTweetContainer.removeAllViews(); //clear container...
                registerSubscription(
                        getPresenter().fetchPreviousTweets().subscribe(MainActivityMVPIC.this::displayTweets)
                );
            }
            else if(view == loginButton){
                getPresenter().toggleLogin(userNameTextView.getText().toString(), userPasswordTextView.getText().toString());
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
        setTitle("MVPIC activity");
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
    public void displayToast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_LONG).show();
    }

    private void displayTweets(List<String> list) {
        pastTweetContainer.removeAllViews();

        for(int i= 0; i < list.size(); i++){
            TextView textView = new TextView(this);
            textView.setText(list.get(i));
            pastTweetContainer.addView(textView);
        }
    }

    @Override
    public void toggleProgressBar(boolean show) {
        Utility.toggleProgressbar(this, show);
    }


    @Override
    public void loggedIn(UserProfile profile) {
        Toast.makeText(this, (profile.getFormattedCredentials() + " Logged in"), Toast.LENGTH_SHORT).show();
        loginButton.setText("Log " + profile.getUserName() + " out");
        container.setVisibility(View.GONE);
    }

    @Override
    public void loggedOut() {
        loginButton.setText(R.string.log_user_in);
        container.setVisibility(View.VISIBLE);
    }
}

package io.patrykpoborca.cleanarchitecture.ui.MVPCI;

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
import io.patrykpoborca.cleanarchitecture.ui.MVPCI.base.BasePresenterActivityMVPCI;
import io.patrykpoborca.cleanarchitecture.ui.MVPCI.interfaces.MainActivityMVPCIPview;
import io.patrykpoborca.cleanarchitecture.ui.MVPCI.models.UserProfile;
import io.patrykpoborca.cleanarchitecture.ui.RouterActivity;
import io.patrykpoborca.cleanarchitecture.util.Utility;

/**
 * Created by Patryk on 7/27/2015.
 */

/**
 * Presenter as a Supervising controller is actually a bridge between observable models and the view, any complex operations are performed within the controller, basic databinding
 * circumvents a lot of unecessary boilerplate.
 *  Model <- Presenter -> View
 */
public class MainActivityMVPCI extends BasePresenterActivityMVPCI<MainMVPCIPresenter> implements MainActivityMVPCIPview {

    @Bind(R.id.fetch_tweet_button) Button fetchTweetButton;
    @Bind(R.id.fetch_last_two_tweets) Button fetchLastTwoButton;
    @Bind(R.id.current_tweet) TextView currentTweetTextView;
    @Bind(R.id.past_tweets_container) LinearLayout pastTweetContainer;
    @Bind(R.id.user_login_button) Button loginButton;
    @Bind(R.id.user_name) TextView userNameTextView;
    @Bind(R.id.user_password) TextView userPasswordTextView;
    @Bind(R.id.container) ViewGroup container;
    @Bind(R.id.some_url) EditText urlText;
    @Bind(R.id.webpage_text) TextView websiteText;
    @Bind(R.id.request_website_button) Button websiteFetchbutton;
    @Bind(R.id.help_history) View helpHistory;
    @Bind(R.id.help_login) View helpLogin;
    @Bind(R.id.help_url) View helpUrl;

    @Inject
    MainMVPCIPresenter presenter;

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
                        getPresenter().fetchPreviousTweets().subscribe(MainActivityMVPCI.this::displayTweets)
                );
            }
            else if(view == loginButton){
                getPresenter().toggleLogin(userNameTextView.getText().toString(), userPasswordTextView.getText().toString());
            }
            else if(view == websiteFetchbutton){
                registerSubscription(getPresenter().loadWebPage(urlText.getText().toString())
                        .subscribe(s -> websiteText.setText(Html.fromHtml(s))));
            }
        }
    };

    private final View.OnClickListener dialogClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == helpHistory){
                new AlertDialog.Builder(MainActivityMVPCI.this)
                        .setMessage(R.string.history_text)
                        .setPositiveButton("Ok", null)
                        .create()
                        .show();
            }
            else if(view == helpUrl){
                new AlertDialog.Builder(MainActivityMVPCI.this)
                        .setMessage(R.string.url_text)
                        .setPositiveButton("Ok", null)
                        .create()
                        .show();
            }
            else if(view == helpLogin){
                new AlertDialog.Builder(MainActivityMVPCI.this)
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
        ButterKnife.bind(this);
        this.fetchLastTwoButton.setOnClickListener(onClickListener);
        this.fetchTweetButton.setOnClickListener(onClickListener);
        this.loginButton.setOnClickListener(onClickListener);
        this.websiteFetchbutton.setOnClickListener(onClickListener);
        this.helpHistory.setOnClickListener(dialogClickListener);
        this.helpLogin.setOnClickListener(dialogClickListener);
        this.helpUrl.setOnClickListener(dialogClickListener);
        setTitle("MVPCI activity");
    }

    @Override
    protected MainMVPCIPresenter getPresenter() {

        if(presenter == null){
            DaggerActivityInjectorComponent.builder()
                    .baseComponent(CleanArchitectureApplication.getBaseComponent())
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


    public static Intent newInstance(Context context) {
        return new Intent(context, MainActivityMVPCI.class);
    }
}

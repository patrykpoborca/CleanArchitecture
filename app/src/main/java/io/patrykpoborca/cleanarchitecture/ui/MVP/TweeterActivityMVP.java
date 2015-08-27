package io.patrykpoborca.cleanarchitecture.ui.MVP;

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
import io.patrykpoborca.cleanarchitecture.ui.MVP.base.BasePresenterActivity;
import io.patrykpoborca.cleanarchitecture.ui.MVP.interfaces.TweeterMVPPView;
import io.patrykpoborca.cleanarchitecture.ui.MVP.interfaces.TweeterMVPPresenter;
import io.patrykpoborca.cleanarchitecture.util.Utility;


public class TweeterActivityMVP extends BasePresenterActivity<TweeterMVPPresenter> implements TweeterMVPPView {

    @Inject
    TweeterMVPPresenter presenter;

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

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == fetchLastTwoButton){
                getPresenter().fetchPreviousTweets();
            }
            else if(view == fetchTweetButton){
                getPresenter().fetchCurrentTweet();
            }
            else if(view == loginButton){
                getPresenter().toggleLogin(userNameTextView.getText().toString(),
                                    userPasswordTextView.getText().toString());
            }
            else if(view == websiteFetchbutton){
                getPresenter().loadWebPage(urlText.getText().toString());
            }

        }
    };

    private final View.OnClickListener dialogClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == helpHistory){
                new AlertDialog.Builder(TweeterActivityMVP.this)
                        .setMessage(R.string.history_text)
                        .setPositiveButton("Ok", null)
                        .create()
                        .show();
            }
            else if(view == helpUrl){
                new AlertDialog.Builder(TweeterActivityMVP.this)
                        .setMessage(R.string.url_text)
                        .setPositiveButton("Ok", null)
                        .create()
                        .show();
            }
            else if(view == helpLogin){
                new AlertDialog.Builder(TweeterActivityMVP.this)
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
        setTitle("MVP Activity IMPL");
    }

    @Override
    protected TweeterMVPPresenter getPresenter() {
        if(presenter == null){

            DaggerActivityInjectorComponent.builder()
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
    public void toggleLoginContainer(boolean b) {
        container.setVisibility(b ? View.VISIBLE : View.GONE);
    }

    @Override
    public void displayWebpage(String html) {
        websiteText.setText(Html.fromHtml(html));
    }

    public static Intent newInstance(Context context) {
        return new Intent(context, TweeterActivityMVP.class);
    }
}

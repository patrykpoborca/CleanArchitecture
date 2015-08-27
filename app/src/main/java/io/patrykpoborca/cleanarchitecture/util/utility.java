package io.patrykpoborca.cleanarchitecture.util;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import rx.Observable;

public class Utility {

    private static final String PROGRESS_TAG = "PROGRESS";

    public static void toggleProgressbar(AppCompatActivity activity, boolean show) {
        try {
            Fragment fragment = activity.getSupportFragmentManager()
                    .findFragmentByTag(PROGRESS_TAG);
            if (show && fragment == null) {
                activity.getSupportFragmentManager().beginTransaction()
                        .add(android.R.id.content, new LoadingFragment(), PROGRESS_TAG)
                        .commit();
            }
            else if (!show && fragment != null) {
                activity.getSupportFragmentManager().beginTransaction()
                        .remove(fragment)
                        .commit();

            }
        } catch (Exception e) {
            Log.e(Utility.class.getSimpleName(), "", e);
        }
    }

    public static void toggleProgressbar(AppCompatActivity activity, Observable observable) {
        toggleProgressbar(activity, true);
        observable.doOnCompleted(() -> toggleProgressbar(activity, false));
    }
}

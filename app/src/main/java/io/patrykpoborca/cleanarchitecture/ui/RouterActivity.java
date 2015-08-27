package io.patrykpoborca.cleanarchitecture.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.patrykpoborca.cleanarchitecture.R;
import io.patrykpoborca.cleanarchitecture.ui.MVP.TweeterActivityMVP;
import io.patrykpoborca.cleanarchitecture.ui.MVPCI.TweeterActivityMVPCI;
import io.patrykpoborca.cleanarchitecture.ui.MVVM.TweeterActivityMVVM;

/**
 * Created by Patryk on 7/28/2015.
 */
public class RouterActivity extends AppCompatActivity {


    @Bind(R.id.stupid_activity)
    View stupidActivity;

    @Bind(R.id.mvp_activity)
    View mvpActivity;

    @Bind(R.id.mvpci_activity)
    View mvpciActivity;

    @Bind(R.id.mvvm_activity)
    View mvvmActivity;

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = null;
            if(view == stupidActivity){
                intent = PlainTweeterActivity.newInstance(RouterActivity.this);
            }
            else if(view == mvpciActivity){
                intent = TweeterActivityMVPCI.newInstance(RouterActivity.this);
            }
            else if(view == mvvmActivity) {
                intent = TweeterActivityMVVM.newInstance(RouterActivity.this);
            }
            else if(view == mvpActivity){
                intent = TweeterActivityMVP.newInstance(RouterActivity.this);
            }

            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_router);
        ButterKnife.bind(this);

        mvvmActivity.setOnClickListener(onClickListener);
        mvpciActivity.setOnClickListener(onClickListener);
        mvpActivity.setOnClickListener(onClickListener);
        stupidActivity.setOnClickListener(onClickListener);
    }
}

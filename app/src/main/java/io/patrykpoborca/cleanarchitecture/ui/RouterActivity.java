package io.patrykpoborca.cleanarchitecture.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.patrykpoborca.cleanarchitecture.R;
import io.patrykpoborca.cleanarchitecture.ui.MVP.MainActivityMVP;
import io.patrykpoborca.cleanarchitecture.ui.MVPIC.MainActivityMVPIC;

/**
 * Created by Patryk on 7/28/2015.
 */
public class RouterActivity extends AppCompatActivity {


    @Bind(R.id.stupid_activity)
    View stupidActivity;

    @Bind(R.id.mvp_activity)
    View mvpActivity;

    @Bind(R.id.mvpic_activity)
    View mvpicActivity;

    @Bind(R.id.mvvm_activity)
    View mvvmActivity;

    @Bind(R.id.mvvmi_activity)
    View mvvmiActivity;
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = null;
            if(view == stupidActivity){
                intent = new Intent(RouterActivity.this, MainActivityStupid.class);
            }
            else if(view == mvpicActivity){
                intent = new Intent(RouterActivity.this, MainActivityMVPIC.class);
            }
            else if(view == mvvmActivity){
//                intent = new Intent(RouterActivity.this, MainActivityStupid.class);
            }
            else if(view == mvvmiActivity){
//                intent = new Intent(RouterActivity.this, MainActivityStupid.class);
            }
            else if(view == mvpActivity){
                intent = new Intent(RouterActivity.this, MainActivityMVP.class);
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
        mvvmiActivity.setOnClickListener(onClickListener);
        mvpicActivity.setOnClickListener(onClickListener);
        mvpActivity.setOnClickListener(onClickListener);
        stupidActivity.setOnClickListener(onClickListener);
    }
}

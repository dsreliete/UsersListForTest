package com.challenge.buscape.buscapeuserslist.UsersDetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.challenge.buscape.buscapeuserslist.Data.model.User;
import com.challenge.buscape.buscapeuserslist.R;
import com.challenge.buscape.buscapeuserslist.Users.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eliete on 7/25/16.
 */
public class DetailActivity extends AppCompatActivity {

    public static final String TAG = DetailActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.email_text)
    TextView emailTextView;
    @BindView(R.id.phone_text)
    TextView phoneTextView;
    @BindView(R.id.street_text)
    TextView streetTextView;
    @BindView(R.id.city_text)
    TextView cityTextView;
    @BindView(R.id.zip_text)
    TextView zipTextView;
    @BindView(R.id.web_text)
    TextView webTextView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        String imageLink = getIntent().getExtras().getString(MainActivity.EXTRA_IMAGE);
        user = (User) getIntent().getExtras().getSerializable(MainActivity.EXTRA_USER);

        Log.e(TAG, "user = " + user);

        user.getAddress().getCity();
        user.getAddress().getStreet();
        user.getAddress().getZipcode();
        user.getEmail();
        user.getPhone();
        user.getWebsite();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




    }
}

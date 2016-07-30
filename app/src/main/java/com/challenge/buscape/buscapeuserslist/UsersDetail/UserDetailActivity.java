package com.challenge.buscape.buscapeuserslist.usersDetail;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.challenge.buscape.buscapeuserslist.R;
import com.challenge.buscape.buscapeuserslist.data.model.User;
import com.challenge.buscape.buscapeuserslist.users.MainActivity;
import com.crashlytics.android.Crashlytics;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by eliete on 7/25/16.
 */
public class UserDetailActivity extends AppCompatActivity implements RuntimePermissionActivity {

    public static final String TAG = UserDetailActivity.class.getSimpleName();
    final private int REQUEST_CODE_ASK_PERMISSIONS = 1325;

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
    @BindView(R.id.dog_image)
    ImageView dogImageView;
    @BindView(R.id.error_text)
    TextView errorTextView;

    private User user;
    private String imageLink;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Crashlytics.log(UserDetailActivity.class.getSimpleName() + " OnCreate");


        if (savedInstanceState != null) {
            user = (User) savedInstanceState.getSerializable(MainActivity.EXTRA_USER);
            imageLink = savedInstanceState.getString(MainActivity.EXTRA_IMAGE);
        } else {
            user = (User) getIntent().getExtras().getSerializable(MainActivity.EXTRA_USER);
            imageLink = getIntent().getExtras().getString(MainActivity.EXTRA_IMAGE);
        }

        getSupportActionBar().setTitle(user.getName());
        showUserDetail();
        showImageLink();
    }

    @OnClick(R.id.email_text)
    public void openEmailClient() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{user.getEmail()});
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @OnClick(R.id.phone_text)
    public void CallToUser() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestAppPermissions(new String[]{Manifest.permission.CALL_PHONE},
                    getResources().getString(R.string.permission_msg), REQUEST_CODE_ASK_PERMISSIONS);
        }else {
            call();
        }
    }


    public void call() throws SecurityException{
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + user.getPhone()));
        startActivity(intent);

    }


    public void showUserDetail() {

        if (user != null) {
            emailTextView.setText(user.getEmail());
            streetTextView.setText(getResources().getString(R.string.user_address,
            user.getAddress().getStreet(), user.getAddress().getSuite()));
            cityTextView.setText(user.getAddress().getCity());
            zipTextView.setText(user.getAddress().getZipcode());
            phoneTextView.setText(user.getPhone());
            webTextView.setText(user.getWebsite());
        }else{
            errorTextView.setVisibility(View.VISIBLE);
            errorTextView.setText(getResources().getString(R.string.no_user));
        }
    }

    public void showImageLink() {
        Picasso.with(this)
                .load(imageLink)
                .placeholder(R.drawable.placeholder)
                .into(dogImageView);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(MainActivity.EXTRA_USER, user);
    }


    @Override
    public void requestAppPermissions(final String[] requestedPermissions, final String message,
                                      final int requestCode) {

        if (ContextCompat.checkSelfPermission(this, requestedPermissions[0])
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, requestedPermissions[0])) {

                Snackbar.make(findViewById(android.R.id.content), message,
                        Snackbar.LENGTH_INDEFINITE).setAction("OK",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ActivityCompat.requestPermissions(UserDetailActivity.this,
                                        requestedPermissions, requestCode);
                            }
                        }).show();
            } else {
                ActivityCompat.requestPermissions(this, requestedPermissions, requestCode);
            }
        } else {
            onPermissionsGranted(requestCode);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    onPermissionsGranted(requestCode);
                }else{
//                    Snackbar.make(this.findViewById(android.R.id.content),
//                            getResources().getString(R.string.permission_msg),
//                            Snackbar.LENGTH_SHORT)
//                            .setActionTextColor(Color.WHITE)
//                            .show();

                    Snackbar.make(findViewById(android.R.id.content),
                            getResources().getString(R.string.permission_msg),
                            Snackbar.LENGTH_INDEFINITE).setAction("ATIVAR",
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent();
                                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                                    intent.setData(Uri.parse("package:" + getPackageName()));
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                    startActivity(intent);
                                }
                            }).show();
                }
                break;
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode) {
        call();
    }
}

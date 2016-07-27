package com.challenge.buscape.buscapeuserslist.Users;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ProgressBar;

import com.challenge.buscape.buscapeuserslist.Data.model.User;
import com.challenge.buscape.buscapeuserslist.R;
import com.challenge.buscape.buscapeuserslist.UsersDetail.DetailActivity;
import com.challenge.buscape.userslist.Data.webservice.Injection;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends InternetDetectionActivity implements MainContract.View {


    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_USER = "user";
    public static final String EXTRA_IMAGE = "link";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    MainContract.UserActionListener userActionListener;
    private UserAdapter adapter;
    private List<User> userList = new ArrayList<>();
    private String imageDetailLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        String imageLink = getImageLinkBasedOnResolutionScreen();
        adapter = new UserAdapter(userList, userTouchListener, imageLink);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        if (MainActivity.hasConnection(this)) {
            userActionListener = new MainPresenter(this, Injection.provideUsersList());
            userActionListener.fetchUserList();
        }else {
            AlertDialogFragment alertDialogFragment = AlertDialogFragment.newInstance();
            alertDialogFragment.show(getSupportFragmentManager(), "alert");
        }
    }

    public String getImageLinkBasedOnResolutionScreen(){
        String link = "";
        String detailLink = "";
        int density= getResources().getDisplayMetrics().densityDpi;
        switch(density)
        {
            case DisplayMetrics.DENSITY_MEDIUM:
                link = "100/150";
                detailLink = "250/250";
                break;
            case DisplayMetrics.DENSITY_HIGH:
                link = "150/225";
                detailLink = "378/378";
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                link = "200/300";
                detailLink = "504/504";
                break;
            case DisplayMetrics.DENSITY_XXHIGH:
                link = "300/450";
                detailLink = "756/756";
                break;
        }
        imageDetailLink = getResources().getString(R.string.image_link, detailLink);
        return getResources().getString(R.string.image_link, link);

    }

    public void showSnackBar(String message) {
        Snackbar.make(this.findViewById(android.R.id.content), message,
                Snackbar.LENGTH_SHORT)
                .setActionTextColor(Color.WHITE)
                .show();
    }

    public static boolean hasConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null){
            return networkInfo.isConnected();
        }else{
            return false;
        }
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showUsersList(List<User> list) {
        userList.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showUserDetailActivity(User user) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(EXTRA_USER, user);
        intent.putExtra(EXTRA_IMAGE, imageDetailLink);
        startActivity(intent);
    }

    UserTouchListener userTouchListener = new UserTouchListener(){
        @Override
        public void onUserClick(User clickedUser) {
            userActionListener.openItemDetails(clickedUser);
        }
    };

    public interface UserTouchListener {
        void onUserClick(User clickedUser);
    }

    @Override
    public void initDownload() {
        userActionListener = new MainPresenter(this, Injection.provideUsersList());
        userActionListener.fetchUserList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userActionListener.onDestroy();
    }
}
